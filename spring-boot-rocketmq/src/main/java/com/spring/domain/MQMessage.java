package com.spring.domain;

import java.io.Serializable;

/**
 * @author shichao
 * @date 2018/6/19 下午4:13
 */
public class MQMessage implements Serializable {

    /**
     * 主题
     */
    private String topic;
    /**
     * tags
     */
    private String tags;
    /**
     * keys
     */
    private String keys;
    /**
     * 顺序，用于发送顺序消息标识
     */
    private Integer index;
    /**
     * data
     */
    private Object data;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MQMessage{" +
                "topic='" + topic + '\'' +
                ", tags='" + tags + '\'' +
                ", keys='" + keys + '\'' +
                ", index=" + index +
                ", data=" + data +
                '}';
    }
}
