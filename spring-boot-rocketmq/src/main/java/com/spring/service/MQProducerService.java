package com.spring.service;

import com.spring.enums.MQMessagTypeEnum;

/**
 * MQ生产者工厂
 *
 * @author shichao
 * @date 2018/7/3 上午9:48
 */
public interface MQProducerService {

    MQProducer getMQProducerByType(MQMessagTypeEnum typeEnum);

}
