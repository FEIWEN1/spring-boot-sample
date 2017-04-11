package com.spring.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ClassName:DirectListener <br/>
 * Title:
 * <p>
 * Direct模式处理器
 * </p>
 * <br/>
 * Date: 2017年4月10日 下午5:22:31 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Component
@RabbitListener(queues = { "direct" }, containerFactory = "rabbitFactory")
public class DirectListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DirectListener.class);

	@RabbitHandler
	public void process(String direct) {
		LOGGER.info("===========>当前处理Direct模式，处理内容{}", direct);
	}

}
