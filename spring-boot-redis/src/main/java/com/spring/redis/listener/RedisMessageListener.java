package com.spring.redis.listener;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.spring.redis.bean.User;

/**
 * ClassName:RedisMessageListener <br/>
 * Title:
 * <p>
 * redis频道监听器
 * </p>
 * <br/>
 * Date: 2017年4月14日 下午4:45:40 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Component
public class RedisMessageListener implements MessageListener {

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RedisMessageListener.class);

	@Override
	public void onMessage(Message message, byte[] pattern) {
		User user = (User) redisTemplate.getValueSerializer().deserialize(
				message.getBody());
		LOGGER.info("redis监听频道publish：{}",
				ReflectionToStringBuilder.toString(user));
	}

}
