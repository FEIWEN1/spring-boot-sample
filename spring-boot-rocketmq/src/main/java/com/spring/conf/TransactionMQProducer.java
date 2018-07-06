package com.spring.conf;

import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author shichao
 * @date 2018/6/29 上午11:37
 */
@Component
@Slf4j
public class TransactionMQProducer extends AbstractMQProducer {

    org.apache.rocketmq.client.producer.TransactionMQProducer producer = new org.apache.rocketmq.client.producer.TransactionMQProducer("transactionProducerGroup");


    @PostConstruct
    public void init() throws MQException {
        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(5);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(10);
        // 队列数
        producer.setCheckRequestHoldMax(500);
        // 消息回查(根据由mq回传的key去查询数据到底是成功还是失败)
        producer.setTransactionCheckListener((msg) -> {
//            msg.getKeys()
            return LocalTransactionState.COMMIT_MESSAGE;
        });
        initMQProducer(producer, MQMessagTypeEnum.TRANSACTION_TYPE);
    }

    @Override
    protected void doSendMessage(Message message, Integer orderId) throws Exception {
        checkProducerExists(producer);
        producer.sendMessageInTransaction(message, (msg, arg) -> {
            // 执行本地事务
            try {
                Integer index = (Integer) arg;
                if (index % 5 == 0) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                if (index == 3) {
                    // 模拟mq发送确认消息失败或事务超时
                    throw new RuntimeException("测试mq消息回查");
                }
            } catch (Exception e) {
                log.error("事务消息发送失败，[{}]", msg, e);
                return LocalTransactionState.UNKNOW;
            }
            return LocalTransactionState.COMMIT_MESSAGE;
        }, orderId);
    }

    @PreDestroy
    public void destroy() {
        super.destroy(producer, MQMessagTypeEnum.TRANSACTION_TYPE);
    }
}
