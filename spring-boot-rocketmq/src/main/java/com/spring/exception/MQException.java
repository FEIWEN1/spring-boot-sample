package com.spring.exception;

/**
 * @author shichao
 * @date 2018/6/15 下午5:53
 */
public class MQException extends Exception {

    public MQException(String message) {
        super(message);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }
}
