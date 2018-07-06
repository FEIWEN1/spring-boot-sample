package com.spring.enums;

/**
 * MQ消息类型枚举
 *
 * @author shichao
 * @date 2018/6/27 下午5:46
 */
public enum MQMessagTypeEnum {

    SIMPLE_TYPE("simple", "普通消息"),

    ORDER_TYPE("order", "顺序消息"),

    TRANSACTION_TYPE("transaction", "事务消息");

    MQMessagTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
