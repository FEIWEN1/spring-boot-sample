package com.spring.redis.service;

import java.util.Date;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.Application;
import com.spring.redis.bean.User;

/**
 * ClassName:UserServiceTest <br/>
 * Title:
 * <p>
 * UserService测试类
 * </p>
 * <br/>
 * Date: 2017年4月11日 下午5:27:17 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void save() {
		User user = new User();
		user.setUsername("demo");
		user.setMobile("12345678911");
		user.setCreateTime(new Date());
		userService.saveUser(user);
		User user2 = userService.getUser(user.getUsername());
		Assert.assertNotNull(user2);
		userService.deleteUser(user.getUsername());
	}

	@Test
	public void increment() {
		long increment = 10L;
		long result = userService.increment("increment", increment);
		Assert.assertEquals(increment, result);
	}

	@Test
	public void list() {
		String key = "userList";
		User u = new User();
		u.setCreateTime(new Date());
		u.setMobile("32132131231");
		u.setUsername("list");
		userService.pushList(key, u);
		User user = userService.popList(key);
		Assert.assertNotNull(user);
	}

	@Test
	public void pushSet() {
		User user = new User();
		user.setUsername("pushSet");
		user.setMobile("12345678911");
		user.setCreateTime(new Date());
		Long result = userService.pushSet(user.getUsername(), user);
		Assert.assertNotNull(result);
		result = userService.removeSet(user.getUsername(), user);
		Assert.assertNotNull(result);
	}

	@Test
	public void getSets() {
		Set<Object> result = userService.getSets("pushSet");
		Assert.assertNotNull(result);
	}

	@Test
	public void isMember() {
		Date date = new Date();
		User user = new User();
		user.setUsername("isMember");
		user.setMobile("12345678911");
		user.setCreateTime(date);
		userService.pushSet(user.getUsername(), user);
		User target = new User();
		target.setUsername("isMember");
		target.setMobile("12345678911");
		target.setCreateTime(date);
		boolean result = userService.isMember(user.getUsername(), target);
		Assert.assertTrue(result);
	}

	@Test
	public void popSet() {
		User user = new User();
		user.setUsername("popSet");
		user.setMobile("12345678911");
		user.setCreateTime(new Date());
		userService.pushSet(user.getUsername(), user);
		User popUser = userService.popSet(user.getUsername());
		Assert.assertNotNull(popUser);
	}

	@Test
	public void pushZset() {
		User user = new User();
		user.setUsername("pushZset");
		user.setMobile("12345678911");
		user.setCreateTime(new Date());
		Boolean result = userService.pushZset(user.getUsername(), user, 10);
		Assert.assertTrue(result);
	}

	@Test
	public void getZset() {
		for (int i = 100; i > 90; i--) {
			User user = new User();
			user.setUsername("getZset");
			user.setMobile("12345678911");
			user.setCreateTime(new Date());
			userService.pushZset(user.getUsername(), user, i);
		}
		TypedTuple<Object> tuple = userService.getZset("getZset");
		Assert.assertEquals(91, tuple.getScore().longValue());
	}

	@Test
	public void zCard() {
		User user = new User();
		user.setUsername("zCard");
		user.setMobile("12345678911");
		user.setCreateTime(new Date());
		userService.pushZset(user.getUsername(), user, 1);
		Long result = userService.zCard("zCard");
		Assert.assertEquals(1, result.longValue());
	}

	@Test
	public void incrementZset() {
		User user = new User();
		user.setUsername("incrementZset");
		user.setMobile("12345678911");
		user.setCreateTime(new Date());
		userService.pushZset(user.getUsername(), user, 1);
		Double result = userService.incrementZset("incrementZset", user, -10);
		Assert.assertEquals(-9, result.longValue());
	}

	@Test
	public void addHash() {
		User user = new User();
		user.setUsername("addHash");
		user.setMobile("12345678911");
		user.setCreateTime(new Date());
		userService.addHash("addHash", "add", user);
	}

	@Test
	public void deleteHash() {
		User user = new User();
		user.setUsername("deleteHash");
		user.setMobile("12345678911");
		user.setCreateTime(new Date());
		userService.addHash("deleteHash", "delete", user);
		userService.deleteHash("deleteHash", "delete");
	}

	@Test
	public void publish() {
		User user = new User();
		user.setUsername("publish");
		user.setMobile("12345678911");
		user.setCreateTime(new Date());
		userService.publish("publish", user);
		userService.publish("String", "字符串测试");
	}

	@Test
	public void getUserByUsername() {
		String username = "saveCacheUser";
		User user = userService.getUserByUsername(username);
		Assert.assertNotNull(user);
	}

	@Test
	public void getUserByMobile() {
		String mobile = "15011111111";
		User user = userService.getUserByMobile(mobile);
		Assert.assertNotNull(user);
	}

	@Test
	public void saveCacheUser() {
		User user = new User();
		user.setUsername("saveCacheUser");
		user.setMobile("15011111111");
		user.setCreateTime(new Date());
		userService.saveCacheUser(user);
	}

	@Test
	public void updateCacheUser() {
		User user = new User();
		user.setUsername("updateCacheUser");
		user.setMobile("18022222111");
		user.setCreateTime(new Date());
		userService.updateCacheUser(user);
	}

	@Test
	public void saveCacheUserCondition() {
		User user = new User();
		user.setUsername("saveCacheUserCondition");
		user.setMobile("18234422111");
		user.setCreateTime(new Date());
		userService.saveCacheUserCondition(user);
	}

	@Test
	public void deleteUserByUsername() {
		User user = new User();
		user.setUsername("saveCacheUserCondition");
		user.setMobile("18234422111");
		user.setCreateTime(new Date());
		userService.saveCacheUser(user);
		userService.deleteUserByUsername(user);
	}

	@Test
	public void deleteAllUser() {
		userService.deleteAllUser();
	}

	@Test
	public void saveString() {
		stringRedisTemplate.boundValueOps("fffffff").set("123456");
	}

	@Test
	public void getUser() {
		User user = new User();
		user.setUsername("getUser");
		user.setMobile("66666666666");
		user.setCreateTime(new Date());
		userService.getUser(user);
	}

	@Test(expected = Exception.class)
	public void callBackRedisException() throws Exception {
		User user = new User();
		user.setUsername("callBackRedisException");
		user.setMobile("66666666666");
		user.setCreateTime(new Date());
		userService.callBackRedisException(user);
	}

}
