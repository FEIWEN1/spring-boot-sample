package com.spring.listener;

import com.spring.bean.MQEvent;
import com.spring.enums.MQMessagTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 普通消息监听处理类
 *
 * @author shichao
 * @date 2018/6/19 下午3:03
 */
@Component
public class SimpleConsumerListener extends AbstractConsumerListener {

    @EventListener(condition = "#event.topic=='simpleTopic' && #event.tags=='tagS'")
    public void simpleListener(MQEvent event) {
        message(MQMessagTypeEnum.SIMPLE_TYPE, event);
    }

    @Override
    protected void doMessage(MQEvent event) {
        if (StringUtils.equals("simpleId:5", event.getKeys())) {
            throw new RuntimeException("测试消息失败");
        }
    }
}
