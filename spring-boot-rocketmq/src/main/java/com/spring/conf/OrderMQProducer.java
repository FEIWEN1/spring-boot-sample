package com.spring.conf;

import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
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
public class OrderMQProducer extends AbstractMQProducer {


    private final DefaultMQProducer producer = new DefaultMQProducer("orderProducerGroup");

    @PostConstruct
    public void initMQProducer() throws MQException {
        super.initMQProducer(producer, MQMessagTypeEnum.ORDER_TYPE);
    }

    @Override
    protected void doSendMessage(Message message, Integer orderId) throws Exception {
        checkProducerExists(producer);
        val result = producer.send(message, (mqs, msg, arg) -> {
            val id = (Integer) arg;
            val index = id % mqs.size();
            return mqs.get(index);
        }, orderId);
//        log.info("顺序消息结果：{}", result);
    }

    @PreDestroy
    public void destroy() {
        super.destroy(producer, MQMessagTypeEnum.ORDER_TYPE);
    }

}
