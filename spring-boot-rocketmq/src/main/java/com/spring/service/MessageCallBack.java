package com.spring.service;

/**
 * 消息处理回调类
 *
 * @author shichao
 * @date 2018/7/3 下午4:57
 */
public interface MessageCallBack<T> {

    /**
     * 消息成功时
     *
     * @return
     */
    T onSuccess();

    /**
     * 消息失败时
     *
     * @return
     */
    T onFailed();

    /**
     * 消息重试超过
     *
     * @param obj
     * @return
     */
    T onExceed(Object obj);

}
