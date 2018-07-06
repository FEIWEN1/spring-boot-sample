package com.spring.listener;

import com.spring.bean.MQEvent;
import com.spring.domain.MQProperties;
import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQMessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息监听消费抽象类，提供处理抽象方法，并提供可包装异常进行重试
 *
 * @author shichao
 * @date 2018/7/3 下午5:46
 */
@Slf4j
abstract class AbstractConsumerListener {

    @Autowired
    protected MQProperties mqProperties;

    /**
     * 消息处理，并包装重试异常类
     *
     * @param event mq消息事件类
     * @throws MQMessageException mq消息消费异常类
     */
    protected final void message(MQMessagTypeEnum topicEnum, MQEvent event) throws MQMessageException {
        log.info("mq事件监听处理{}类型消息，{}", topicEnum.getType(), event.getMessage());
        try {
            doMessage(event);
        } catch (Exception e) {
            if (e instanceof MQMessageException) {
                throw e;
            }
            throw new MQMessageException("mq消息消费异常", e, getRetryNum(topicEnum, event));
        }
    }

    /**
     * 消息具体业务实现，如需控制重试机制，请包装并抛出MQMessageException，否则系统将按配置文件设置重试
     *
     * @param event mq消息事件类
     */
    protected abstract void doMessage(MQEvent event);

    /**
     * 获取指定topic重试次数
     *
     * @param topicEnum MQ消息类型枚举
     * @param event     mq消息事件类
     * @return
     */
    private int getRetryNum(MQMessagTypeEnum topicEnum, MQEvent event) {
        if (null == topicEnum) {
            log.warn("监听mq消息topic枚举为null，请检查业务代码");
            return 0;
        }
        return mqProperties.getTopicMap().get(topicEnum.getType())
                .getTopicInfos().get(event.getTopic()).getRetryNum();
    }


}
