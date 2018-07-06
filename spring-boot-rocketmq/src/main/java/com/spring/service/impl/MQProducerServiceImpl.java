package com.spring.service.impl;

import com.spring.service.MQProducer;
import com.spring.enums.MQMessagTypeEnum;
import com.spring.service.MQProducerService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author shichao
 * @date 2018/7/3 上午9:49
 */
@Service
public class MQProducerServiceImpl implements MQProducerService {

    @Autowired
    private Map<String, MQProducer> producerMap;

    private static final String MQPRODUCER = "%sMQProducer";

    @Override
    public MQProducer getMQProducerByType(MQMessagTypeEnum typeEnum) {
        if (null == typeEnum || MapUtils.isEmpty(producerMap)) {
            return null;
        }
        return producerMap.get(String.format(MQPRODUCER, typeEnum.getType()));
    }

}
