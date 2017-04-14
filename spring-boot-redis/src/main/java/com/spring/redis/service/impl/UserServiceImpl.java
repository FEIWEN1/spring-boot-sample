package com.spring.redis.service.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.spring.redis.bean.User;
import com.spring.redis.service.UserService;

/**
 * ClassName:UserServiceImpl <br/>
 * Title:
 * <p>
 * UserService接口实现
 * </p>
 * <br/>
 * Date: 2017年4月11日 下午5:22:56 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public void saveUser(User user) {
		redisTemplate.boundValueOps(user.getUsername()).set(user);
	}

	@Override
	public User getUser(String username) {
		return (User) redisTemplate.boundValueOps(username).get();
	}

	@Override
	public void deleteUser(String username) {
		redisTemplate.delete(username);
	}

	@Override
	public long increment(String key, long increment) {
		deleteUser(key);
		return redisTemplate.boundValueOps(key).increment(increment);
	}

	@Override
	public User popList(String key) {
		return (User) redisTemplate.boundListOps(key).rightPop(10,
				TimeUnit.SECONDS);
	}

	@Override
	public void pushList(String key, User user) {
		redisTemplate.boundListOps(key).leftPush(user);
	}

	@Override
	public Long pushSet(String key, User user) {
		return redisTemplate.boundSetOps(key).add(user);
	}

	@Override
	public Long removeSet(String key, User user) {
		return redisTemplate.boundSetOps(key).remove(user);
	}

	@Override
	public Set<Object> getSets(String key) {
		return redisTemplate.boundSetOps(key).members();
	}

	@Override
	public boolean isMember(String key, Object obj) {
		return redisTemplate.boundSetOps(key).isMember(obj);
	}

	@Override
	public User popSet(String key) {
		return (User) redisTemplate.boundSetOps(key).pop();
	}

	@Override
	public Boolean pushZset(String key, User user, double score) {
		return redisTemplate.boundZSetOps(key).add(user, score);
	}

	@Override
	public TypedTuple<Object> getZset(String key) {
		Set<TypedTuple<Object>> sets = redisTemplate.boundZSetOps(key)
				.rangeWithScores(0, -1);
		if (CollectionUtils.isEmpty(sets)) {
			return null;
		}
		return sets.iterator().next();
	}

	@Override
	public Long zCard(String key) {
		return redisTemplate.boundZSetOps(key).zCard();
	}

	@Override
	public Double incrementZset(String key, User user, double increment) {
		return redisTemplate.boundZSetOps(key).incrementScore(user, increment);
	}

	@Override
	public void addHash(String key, String hashKey, User user) {
		redisTemplate.boundHashOps(key).put(hashKey, user);
	}

	@Override
	public void deleteHash(String key, String hashKey) {
		redisTemplate.boundHashOps(key).delete(hashKey);
	}

	@Override
	public void publish(String channel, Object user) {
		redisTemplate.convertAndSend(channel, user);
	}

}
