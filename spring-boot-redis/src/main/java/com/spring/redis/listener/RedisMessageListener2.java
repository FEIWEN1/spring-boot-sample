package com.spring.redis.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * ClassName:RedisMessageListener2 <br/>
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
public class RedisMessageListener2 implements MessageListener {

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RedisMessageListener2.class);

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String result = redisTemplate.getValueSerializer()
				.deserialize(message.getBody()).toString();
		LOGGER.info("redis监听频道2String：{}", result);
	}

}
