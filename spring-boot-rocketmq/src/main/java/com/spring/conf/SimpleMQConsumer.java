package com.spring.conf;

import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQException;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author shichao
 * @date 2018/6/15 下午5:33
 */
@Component
public class SimpleMQConsumer extends AbstractMQConsumer {

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("simpleConsumerGroup");

    @PostConstruct
    public void init() throws MQException {
        super.init(consumer, MQMessagTypeEnum.SIMPLE_TYPE);
    }

    @PreDestroy
    public void destroy() {
        destroy(consumer, MQMessagTypeEnum.SIMPLE_TYPE);
    }

}
