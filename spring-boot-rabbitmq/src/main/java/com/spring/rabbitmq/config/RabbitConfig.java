package com.spring.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:RabbitConfig <br/>
 * Title:
 * <p>
 * 模式配置
 * </p>
 * <br/>
 * Date: 2017年4月10日 下午5:17:21 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Configuration
public class RabbitConfig {

	@Bean
	public Queue directQueue() {
		return new Queue("direct");
	}

	@Bean
	public Queue topicQueue() {
		return new Queue("topic.msg");
	}

	@Bean
	public Queue topicLikeQueue() {
		return new Queue("topic.like");
	}

	@Bean
	public Queue fanoutAQueue() {
		return new Queue("fanout-A");
	}

	@Bean
	public Queue fanoutBQueue() {
		return new Queue("fanout-B");
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("topicExchange");
	}

	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}

	@Bean
	Binding bindingMessage() {
		// 只匹配topic.msg
		return BindingBuilder.bind(topicQueue()).to(exchange())
				.with("topic.msg");
	}

	@Bean
	Binding bindingLikeMessage() {
		// 同时匹配两个队列
		return BindingBuilder.bind(topicLikeQueue()).to(exchange())
				.with("topic.#");
	}

	@Bean
	Binding bindingFanoutA() {
		return BindingBuilder.bind(fanoutAQueue()).to(fanoutExchange());
	}

	@Bean
	Binding bindingFanoutB() {
		return BindingBuilder.bind(fanoutBQueue()).to(fanoutExchange());
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer,
			ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setConcurrentConsumers(2);
		factory.setMaxConcurrentConsumers(10);
		return factory;
	}

}
