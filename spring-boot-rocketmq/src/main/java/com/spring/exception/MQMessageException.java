package com.spring.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;

/**
 * mq消息消费异常类，提供重试次数
 *
 * @author shichao
 * @date 2018/7/3 下午5:05
 */

public class MQMessageException extends RuntimeException {

    /**
     * 重试次数
     */
    @Setter
    @Getter
    private int retryNum = 0;

    @Setter
    @Getter
    private ConsumeOrderlyStatus orderlyStatus;

    public MQMessageException(String message, int retryNum, ConsumeOrderlyStatus orderlyStatus) {
        super(message);
        this.retryNum = retryNum;
        this.orderlyStatus = orderlyStatus;
    }

    public MQMessageException(String message, Throwable cause, int retryNum) {
        super(message, cause);
        this.retryNum = retryNum;
    }

}
