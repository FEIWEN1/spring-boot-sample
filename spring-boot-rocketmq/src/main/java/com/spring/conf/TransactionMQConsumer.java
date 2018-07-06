package com.spring.conf;

import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQException;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 事务消息消费者
 *
 * @author shichao
 * @date 2018/7/3 下午5:54
 */
@Component
public class TransactionMQConsumer extends AbstractMQConsumer {

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transactionConsumerGroup");

    @PostConstruct
    public void init() throws MQException {
        super.init(consumer, MQMessagTypeEnum.TRANSACTION_TYPE);
    }

    @PreDestroy
    public void destroy() {
        destroy(consumer, MQMessagTypeEnum.TRANSACTION_TYPE);
    }

}
