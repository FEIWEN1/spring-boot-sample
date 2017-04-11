package com.spring.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ClassName:TopicListener <br/>
 * Title:
 * <p>
 * topic处理器
 * </p>
 * <br/>
 * Date: 2017年4月11日 上午10:05:59 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Component
@RabbitListener(queues = { "topic.msg" }, containerFactory = "rabbitFactory")
public class TopicListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TopicListener.class);

	@RabbitHandler
	public void process(String topic) {
		LOGGER.info("===========>当前处理topic模式，处理内容{}", topic);
	}

}
