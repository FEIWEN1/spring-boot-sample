package com.spring.conf;

import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 普通消息生产者
 *
 * @author shichao
 * @date 2018/6/15 下午5:31
 */
@Component
@Slf4j
public class SimpleMQProducer extends AbstractMQProducer {

    private final DefaultMQProducer producer = new DefaultMQProducer("simpleProducerGroup");

    @PostConstruct
    public void initMQProducer() throws MQException {
        super.initMQProducer(producer, MQMessagTypeEnum.SIMPLE_TYPE);
    }

    @Override
    protected void doSendMessage(Message message, Integer orderId) throws Exception {
        checkProducerExists(producer);
        producer.send(message, new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("mq生产者{}普通消息发送成功，{}", producer.getProducerGroup(), sendResult);
            }

            @Override
            public void onException(Throwable e) {
                log.error("mq生产者{}普通消息发送失败，{}", producer.getProducerGroup(), e);
            }

        });
    }

    @PreDestroy
    public void destroy() {
        super.destroy(producer, MQMessagTypeEnum.SIMPLE_TYPE);
    }

}
