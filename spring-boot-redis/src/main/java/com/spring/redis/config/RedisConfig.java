package com.spring.redis.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@EnableCaching
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

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(
			RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		// 开启事务支持
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}

	// 自定义KeyGenerator
	@Bean
	public KeyGenerator redisKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method,
					Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

}
