package com.spring.bean;

import lombok.Data;

/**
 * mq topic基本信息
 *
 * @author shichao
 * @date 2018/7/4 上午10:19
 */
@Data
public class TopicInfo {

    /**
     * 消费tags
     */
    private String tags;

    /**
     * 重试次数
     */
    private int retryNum = 3;

}
