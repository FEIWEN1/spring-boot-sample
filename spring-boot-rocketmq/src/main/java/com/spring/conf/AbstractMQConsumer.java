package com.spring.conf;

import com.spring.bean.MQEvent;
import com.spring.domain.MQProperties;
import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQException;
import com.spring.exception.MQMessageException;
import com.spring.service.MessageCallBack;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消息消费者抽象类，实现各类型消费者初始化及订阅，注册相关监听，具体监听由子类实现，提供消息重试机制
 *
 * @author shichao
 * @date 2018/6/15 下午5:33
 */
@Slf4j
abstract class AbstractMQConsumer {

    @Autowired
    protected MQProperties properties;

    @Autowired
    protected ApplicationEventPublisher publisher;

    /**
     * 初始化mq消费者
     *
     * @param consumer  消费者
     * @param topicEnum MQ消息类型枚举{@link MQMessagTypeEnum}
     * @throws MQException
     */
    protected final void init(DefaultMQPushConsumer consumer, MQMessagTypeEnum topicEnum) throws MQException {
        val flag = properties.checkProp(topicEnum);
        if (!flag) {
            log.warn("rocketmq消费者[{}],配置文件缺少相关属性,放弃初始化", consumer.getConsumerGroup());
            return;
        }
        log.info("rocketmq消费者[{}]初始化start", consumer.getConsumerGroup());
        setup(consumer);
        subscribe(consumer, topicEnum);
        registerMessageListener(consumer);
        start(consumer, topicEnum);
    }

    /**
     * 配置consumer，集群/广播
     */
    protected void setup(DefaultMQPushConsumer consumer) {
        consumer.setNamesrvAddr(properties.getNamesrvAddr());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setConsumeThreadMin(30);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 设置批量消费，以提升消费吞吐量，默认是1
        consumer.setConsumeMessageBatchMaxSize(1);
    }

    /**
     * 订阅topic
     *
     * @throws MQException
     */
    private void subscribe(DefaultMQPushConsumer consumer, MQMessagTypeEnum topicEnum) throws MQException {
        // 消息存在订阅成功标识
        val subscribed = new AtomicBoolean(false);
        properties.getTopicMap().get(topicEnum.getType()).getTopicInfos().forEach((topic, topicInfo) -> {
            try {
                consumer.subscribe(topic, topicInfo.getTags());
                subscribed.set(Boolean.TRUE);
            } catch (MQClientException e) {
                log.error("mq消息订阅失败，topic:[{}],tags:[{}]", topic, topicInfo.getTags(), e);
            }
        });
        if (!subscribed.get()) {
            throw new MQException("mq订阅所有topic均失败");
        }
    }


    /**
     * 注册消息监听
     */
    @SuppressWarnings("unchecked")
    private void registerMessageListener(DefaultMQPushConsumer consumer) {
        consumer.registerMessageListener(messageListener(consumer));
    }

    /**
     * 监听，消息处理
     *
     * @return
     */
    protected MessageListener messageListener(DefaultMQPushConsumer consumer) {
        return (MessageListenerConcurrently) (msgs, context) -> {
            if (CollectionUtils.isEmpty(msgs)) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            // 此处只处理1条
            val messageExt = msgs.get(0);
            return doMessage(new MQEvent(consumer, messageExt),
                    new MessageCallBack<ConsumeConcurrentlyStatus>() {
                        @Override
                        public ConsumeConcurrentlyStatus onSuccess() {
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }

                        @Override
                        public ConsumeConcurrentlyStatus onFailed() {
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                        }

                        @Override
                        public ConsumeConcurrentlyStatus onExceed(Object obj) {
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    });
        };
    }

    /**
     * 消费者消息处理，重试机制实现
     *
     * @param event    消息事件类
     * @param callBack 消息处理回调类
     * @param <T>      消息成功或重试类泛型
     * @return
     */
    protected final <T> T doMessage(MQEvent event, MessageCallBack<T> callBack) {
        val messageExt = event.getMessageExt();
        val reconsumeTimes = messageExt.getReconsumeTimes();
        try {
            publisher.publishEvent(event);
        } catch (MQMessageException e) {
            // 日志记录
            log.error("消息到达处理失败,重试次数{},[{}]", reconsumeTimes, event, e);
            val retryNum = e.getRetryNum();
            if (reconsumeTimes >= retryNum) {
                //TODO 记录数据库,需要人为检查失败原因
                log.warn("消息重试超过指定数量{}", retryNum);
                return callBack.onExceed(e.getOrderlyStatus());
            } else if (retryNum <= 0) {
                //不需重试
            } else {
                return callBack.onFailed();
            }
        }
        //如出现其他异常则说明程序异常，不处理，需检查对应服务器和业务代码
        return callBack.onSuccess();
    }


    /**
     * 延迟3秒再启动，主要是等待spring事件监听相关程序初始化完成，
     * 否则，会出现对RocketMQ的消息进行消费后立即发布消息到达的事件，
     * 然而此事件的监听程序还未初始化，从而造成消息的丢失
     */
    private void start(DefaultMQPushConsumer consumer, MQMessagTypeEnum topicEnum) {
        val sleepTime = properties.getTopicMap().get(topicEnum.getType()).getSleepTime();
        final long time = sleepTime <= 3000 ? 3000 : sleepTime;
        new Thread(() -> {
            try {
                Thread.sleep(time);
                consumer.start();
                log.info("rocketmq消费者[{}]初始化成功", consumer.getConsumerGroup());
            } catch (Exception e) {
                log.error("rocketmq消费者[{}]启动失败", consumer.getConsumerGroup(), e);
            }
        }).start();
    }

    /**
     * 服务关闭时mq消费者的操作
     *
     * @param consumer  消费者
     * @param topicEnum MQ消息类型枚举
     */
    protected final void destroy(DefaultMQPushConsumer consumer, MQMessagTypeEnum topicEnum) {
        val flag = properties.checkProp(topicEnum);
        if (!flag) {
            return;
        }
        properties.getTopicMap().get(topicEnum.getType()).getTopicInfos().forEach((topic, topicInfo) ->
                consumer.unsubscribe(topic)
        );
        consumer.shutdown();

        log.info("rocketmq消费者[{}]已关闭", consumer.getConsumerGroup());
    }

}

