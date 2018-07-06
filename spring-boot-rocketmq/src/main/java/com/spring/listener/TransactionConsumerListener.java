package com.spring.listener;

import com.spring.bean.MQEvent;
import com.spring.enums.MQMessagTypeEnum;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事务消息监听处理类
 *
 * @author shichao
 * @date 2018/7/4 下午2:18
 */
@Component
public class TransactionConsumerListener extends AbstractConsumerListener {

    @EventListener(condition = "#event.topic=='transactionTopic' && #event.tags=='tagT'")
    public void transactionListener(MQEvent event) {
        message(MQMessagTypeEnum.TRANSACTION_TYPE, event);
    }

    @Override
    protected void doMessage(MQEvent event) {

    }

}
