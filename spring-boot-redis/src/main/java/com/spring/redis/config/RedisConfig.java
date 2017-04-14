package com.spring.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.spring.redis.listener.RedisMessageListener;
import com.spring.redis.listener.RedisMessageListener2;

/**
 * ClassName:RedisConfig <br/>
 * Title:
 * <p>
 * 初始化redis订阅bean
 * </p>
 * <br/>
 * Date: 2017年4月14日 下午4:55:03 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Configuration
public class RedisConfig {

	@Autowired
	private RedisMessageListener messageListener;
	@Autowired
	private RedisMessageListener2 messageListener2;

	@Bean(name = "redisContainer")
	public RedisMessageListenerContainer redisMessageListenerContainer(
			RedisConnectionFactory redisConnectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory);
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(
				messageListener);
		ChannelTopic topic = new ChannelTopic("publish");
		MessageListenerAdapter listenerAdapter2 = new MessageListenerAdapter(
				messageListener2);
		ChannelTopic topic2 = new ChannelTopic("String");
		container.addMessageListener(listenerAdapter, topic);
		container.addMessageListener(listenerAdapter2, topic2);
		return container;
	}

}
