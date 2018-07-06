package com.spring.controller;

import com.spring.domain.MQMessage;
import com.spring.enums.MQMessagTypeEnum;
import com.spring.exception.MQException;
import com.spring.service.MQProducerService;
import lombok.val;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 消息测试控制器
 *
 * @author shichao
 * @date 2018/6/19 下午4:07
 */
@RestController
@RequestMapping("/message")
public class MQTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MQTestController.class);

    @Autowired
    private MQProducerService producerService;

    @Autowired
    private ThreadPoolTaskExecutor orderTopicExecutor;

    /**
     * 测试普通消息
     *
     * @return
     */
    @RequestMapping("/simple")
    public String sendSimpleMessage() {
        val successList = new ArrayList<String>();
        val failedList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            val mqMessage = new MQMessage();
            mqMessage.setTopic("simpleTopic");
            mqMessage.setTags("tagS");
            mqMessage.setKeys("simpleId:" + i);
            mqMessage.setData("simple message " + i);
            try {
                producerService.getMQProducerByType(MQMessagTypeEnum.SIMPLE_TYPE)
                        .sendMessage(mqMessage);
                successList.add(String.valueOf(i));
            } catch (MQException e) {
                failedList.add(String.valueOf(i));
                LOGGER.error("普通消息发送失败,{}", mqMessage, e);
            }
        }
        return buildMessage("普通", successList, failedList);
    }

    /**
     * 测试顺序消息
     *
     * @return
     */
    @RequestMapping("/order")
    public String sendOrderMessage() throws Exception {
        val successList = new CopyOnWriteArrayList<String>();
        val failedList = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 4; i++) {
            final int n = i;
            orderTopicExecutor.submit(() -> {
                for (int j = 0; j < 6; j++) {
                    val mqMessage = new MQMessage();
                    mqMessage.setTopic("orderTopic");
                    mqMessage.setTags("tagO");
                    mqMessage.setKeys("orderId:" + j);
                    mqMessage.setData(String.format("order message:index[%s],que:[%s]", n, j));
                    mqMessage.setIndex(n);
                    String index = String.format("%s,%s", n, j);
                    try {
                        if (j == 3) {
                            Thread.sleep(100);
                        }
                        producerService.getMQProducerByType(MQMessagTypeEnum.ORDER_TYPE)
                                .sendMessage(mqMessage);
                        successList.add(index);
                    } catch (Exception e) {
                        failedList.add(index);
                        LOGGER.error("顺序消息发送失败,{}", mqMessage, e);
                    }
                }
            });
        }
        Thread.sleep(1000);
        return buildMessage("顺序", successList, failedList);
    }

    /**
     * 测试事务消息
     *
     * @return
     */
    @RequestMapping("/transaction")
    public String sendTransactionMessage() {
        val successList = new ArrayList<String>();
        val failedList = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            val mqMessage = new MQMessage();
            mqMessage.setTopic("transactionTopic");
            mqMessage.setTags("tagT");
            mqMessage.setKeys("transactionId:");
            mqMessage.setData("transaction message ");
            try {
                producerService.getMQProducerByType(MQMessagTypeEnum.TRANSACTION_TYPE)
                        .sendMessage(mqMessage);
                successList.add(String.valueOf(i));
            } catch (Exception e) {
                failedList.add(String.valueOf(i));
                LOGGER.error("事务消息发送失败,{}", mqMessage, e);
            }
        }
        return buildMessage("事务", successList, failedList);
    }

    /**
     * 构建消息发送结果
     *
     * @param type        类型
     * @param successList 成功list
     * @param failedList  失败list
     * @return
     */
    private String buildMessage(@NotBlank String type, @NotNull List<String> successList, @NotNull List<String> failedList) {
        val sb = new StringBuilder(type);
        sb.append("消息发送完毕。")
                .append("成功").append(successList.size()).append("条").append(successList)
                .append(",")
                .append("失败").append(failedList.size()).append("条").append(failedList);
        return sb.toString();
    }

}
