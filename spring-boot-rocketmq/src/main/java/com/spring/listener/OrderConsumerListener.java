package com.spring.listener;

import com.spring.bean.MQEvent;
import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQMessageException;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 顺序消息监听处理类，开源版无法COMMIT、ROLLBACK，仅做测试用
 *
 * @author shichao
 * @date 2018/6/28 下午3:27
 */
@Component
public class OrderConsumerListener extends AbstractConsumerListener {

    @EventListener(condition = "#event.topic=='orderTopic' && #event.tags=='tagO'")
    public void orderListener(MQEvent event) {
        message(MQMessagTypeEnum.ORDER_TYPE, event);
    }

    @Override
    protected void doMessage(MQEvent event) {
        if (StringUtils.equals("order message:index[2],que:[2]", event.getMessage())) {
            throw new MQMessageException("测试顺序消息失败", 2, ConsumeOrderlyStatus.COMMIT);
        }
        if (StringUtils.equals("order message:index[3],que:[3]", event.getMessage())) {
            throw new MQMessageException("测试顺序消息失败", 2, ConsumeOrderlyStatus.ROLLBACK);
        }
    }

}
