package com.spring.bean;


import lombok.Data;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;

/**
 * mq消息事件类
 *
 * @author shichao
 * @date 2018/6/19 下午2:31
 */
@Data
public class MQEvent extends ApplicationEvent {

    /**
     * mq topic
     */
    private String topic;
    /**
     * mq tags
     */
    private String tags;
    /**
     * mq keys
     */
    private String keys;
    /**
     * mq消费者
     */
    private DefaultMQPushConsumer consumer;
    /**
     * 消息实体
     */
    private MessageExt messageExt;
    /**
     * 消息内容
     */
    private String message;

    public MQEvent(DefaultMQPushConsumer consumer, MessageExt messageExt) {
        super(messageExt);
        this.topic = messageExt.getTopic();
        this.tags = messageExt.getTags();
        this.keys = messageExt.getKeys();
        this.consumer = consumer;
        this.messageExt = messageExt;
        try {
            this.message = MQObjectMapper.getInstance().readValue(messageExt.getBody(), String.class);
        } catch (Exception e) {
        }
    }

}
