package com.spring.rabbitmq.service.impl;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rabbitmq.service.RabbitService;

/**
 * ClassName:RabbitServiceImpl <br/>
 * Title:
 * <p>
 * RabbitService实现
 * </p>
 * <br/>
 * Date: 2017年4月10日 下午5:31:46 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Service("rabbitService")
public class RabbitServiceImpl implements RabbitService {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public void directSend() {
		String str = "direct";
		amqpTemplate.convertAndSend(str, send(str));
	}

	@Override
	public void topicSend() {
		String str = "topic.msg";
		String str1 = "topic.like";
		String str2 = "topic.1";
		// 匹配topic.#和topic.msg
		amqpTemplate.convertAndSend("topicExchange", str, send(str));
		// 只匹配topic.#
		amqpTemplate.convertAndSend("topicExchange", str1, send(str1));
		// 只匹配topic.#
		amqpTemplate.convertAndSend("topicExchange", str2, send(str2));
	}

	@Override
	public void headersSend() {
		send("headers");
	}

	@Override
	public void fanoutSend() {
		amqpTemplate.convertAndSend("fanoutExchange", "", send("fanout"));
	}

	private String send(String mode) {
		return String.format("%s,%s", mode, new Date());
	}

}
