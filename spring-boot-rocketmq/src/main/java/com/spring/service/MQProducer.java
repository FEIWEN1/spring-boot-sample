package com.spring.service;

import com.spring.domain.MQMessage;
import com.spring.exception.MQException;

/**
 * mq消息发送接口，支持普通、顺序、事务消息
 *
 * @author shichao
 * @date 2018/6/29 下午4:36
 */
public interface MQProducer {

    /**
     * 发送mq消息
     *
     * @param mqMessage 消息实体
     * @throws MQException mq操作异常
     */
    void sendMessage(MQMessage mqMessage) throws MQException;

}