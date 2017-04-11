package com.spring.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ClassName:FanoutBListener <br/>
 * Title:
 * <p>
 * Fanout模式处理器
 * </p>
 * <br/>
 * Date: 2017年4月11日 下午1:19:01 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Component
@RabbitListener(queues = { "fanout-B" }, containerFactory = "rabbitFactory")
public class FanoutBListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FanoutBListener.class);

	@RabbitHandler
	public void process(String fanout) {
		LOGGER.info("===========>fanout-B当前处理fanout模式，处理内容{}", fanout);
	}
}
