package com.spring.conf;

import com.spring.bean.MQEvent;
import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQException;
import com.spring.service.MessageCallBack;
import lombok.val;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 有序消息消费者
 *
 * @author shichao
 * @date 2018/6/28 上午11:26
 */
@Component
public class OrderMQConsumer extends AbstractMQConsumer {

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("orderConsumerGroup");

    @PostConstruct
    public void init() throws MQException {
        super.init(consumer, MQMessagTypeEnum.ORDER_TYPE);
    }

    @Override
    protected MessageListener messageListener(DefaultMQPushConsumer consumer) {
        return (MessageListenerOrderly) (msgs, context) -> {
            if (CollectionUtils.isEmpty(msgs)) {
                return ConsumeOrderlyStatus.SUCCESS;
            }
            context.setAutoCommit(true);
            val messageExt = msgs.get(0);
            return super.doMessage(new MQEvent(consumer, messageExt),
                    new MessageCallBack<ConsumeOrderlyStatus>() {
                        @Override
                        public ConsumeOrderlyStatus onSuccess() {
                            return ConsumeOrderlyStatus.SUCCESS;
                        }

                        @Override
                        public ConsumeOrderlyStatus onFailed() {
                            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                        }

                        @Override
                        public ConsumeOrderlyStatus onExceed(Object obj) {
                            return obj instanceof ConsumeOrderlyStatus ?
                                    (ConsumeOrderlyStatus) obj : ConsumeOrderlyStatus.SUCCESS;
                        }
                    });
        };
    }

    @PreDestroy
    public void destroy() {
        destroy(consumer, MQMessagTypeEnum.ORDER_TYPE);
    }


}
