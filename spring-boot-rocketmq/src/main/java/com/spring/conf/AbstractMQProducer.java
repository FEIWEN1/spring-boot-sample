package com.spring.conf;

import com.spring.bean.MQObjectMapper;
import com.spring.domain.MQMessage;
import com.spring.domain.MQProperties;
import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQException;
import com.spring.service.MQProducer;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 消息生产者抽象类，实现消息发送业务，具体使用的生成者由子类实现
 *
 * @author shichao
 * @date 2018/6/15 下午5:31
 */
@Slf4j
abstract class AbstractMQProducer implements MQProducer {

    @Autowired
    protected MQProperties mqProperties;

    /**
     * mq生产者集合
     */
    protected final CopyOnWriteArrayList<DefaultMQProducer> producerList = new CopyOnWriteArrayList<>();

    /**
     * 初始化mq生产者
     *
     * @param producer  mq生产者
     * @param topicEnum mq消息类型枚举
     * @throws MQException
     */
    protected final void initMQProducer(DefaultMQProducer producer, MQMessagTypeEnum topicEnum) throws MQException {
        boolean flag = mqProperties.checkProp(topicEnum);
        if (!flag) {
            log.warn("rocketmq生产者[{}],配置文件缺少相关属性,放弃初始化", producer.getProducerGroup());
            return;
        }
        try {
            log.info("rocketmq生产者[{}]初始化开始", producer.getProducerGroup());

            producer.setNamesrvAddr(mqProperties.getNamesrvAddr());
            producer.setRetryTimesWhenSendAsyncFailed(10);
            producer.setSendMessageWithVIPChannel(false);
            producer.start();
            producerList.add(producer);

            log.info("rocketmq生产者[{}]初始化完成", producer.getProducerGroup());
        } catch (Exception e) {
            log.error("rocketmq生产者[{}]初始化失败", producer.getProducerGroup(), e);
            throw new MQException(String.format("rocketmq生产者%s启动失败",
                    producer.getProducerGroup()), e);
        }
    }

    @Override
    public void sendMessage(MQMessage mqMessage) throws MQException {
        try {
            val message = buildMessage(mqMessage);
            doSendMessage(message, mqMessage.getIndex());
        } catch (Exception e) {
            String messsage = "rocketmq生产者消息error";
            if (e instanceof MQException) {
                messsage = e.getMessage();
            }
            throw new MQException(messsage, e);
        }
    }

    /**
     * 校验当前mq生产者是否已初始化
     *
     * @param producer mq生产者
     * @throws MQException
     */
    protected final void checkProducerExists(final DefaultMQProducer producer) throws MQException {
        if (!CollectionUtils.exists(producerList, obj ->
                ObjectUtils.equals(obj, producer)
        )) {
            throw new MQException(
                    String.format("rocketmq生产者%s未初始化，消息被拒绝", producer.getProducerGroup()));
        }
    }

    /**
     * 通过具体生产者发送消息
     *
     * @param message 消息实体
     * @param orderId 顺序Id，用于发送顺序消息时使用
     * @throws Exception
     */
    protected abstract void doSendMessage(Message message, Integer orderId) throws Exception;

    /**
     * 验证参数并build message
     *
     * @param message 消息实体
     * @return
     * @throws Exception
     */
    private Message buildMessage(MQMessage message) throws Exception {
        Assert.notNull(message);
        val data = message.getData();
        Assert.notNull(data);
        Assert.notNull(message.getTopic());
        val body = MQObjectMapper.getInstance().writeValueAsBytes(data);
        return new Message(message.getTopic(),
                message.getTags(), message.getKeys(), body);
    }

    /**
     * 关闭生产者
     *
     * @param producer 生产者
     */
    public void destroy(DefaultMQProducer producer, MQMessagTypeEnum topicEnum) {
        boolean flag = mqProperties.checkProp(topicEnum);
        if (!flag) {
            return;
        }
        producer.shutdown();

        log.info("rocketmq生产者[{}]已关闭", producer.getProducerGroup());
    }

}
