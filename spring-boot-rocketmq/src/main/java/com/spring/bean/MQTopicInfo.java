package com.spring.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * mq基本信息
 *
 * @author shichao
 * @date 2018/6/27 下午5:52
 */
@Data
public class MQTopicInfo {

    /**
     * mq消费者启动前休眠时间
     */
    private long sleepTime = 0L;

    /**
     * mq topic:topicInfo
     */
    private Map<String, TopicInfo> topicInfos = new HashMap<>();

}
