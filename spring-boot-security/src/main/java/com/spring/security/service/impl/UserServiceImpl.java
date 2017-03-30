package com.spring.security.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.MapUtils;

import com.spring.security.bean.Role;
import com.spring.security.domain.User;
import com.spring.security.service.UserService;

/**
 * ClassName:UserServiceImpl <br/>
 * Title:
 * <p>
 * UserService实现，暂时用户初始化未使用数据库获取
 * </p>
 * <br/>
 * Date: 2017年3月29日 下午2:45:06 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private Map<String, User> users = new HashMap<>();

	@PostConstruct
	public void initUser() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		user.setRole(Role.ADMIN.getName());
		users.put(user.getUsername(), user);
		user = new User();
		user.setUsername("shichao");
		user.setPassword("ba519ed1eac333f439ffe67fcb836f48");
		user.setRole(Role.USER.getName());
		users.put(user.getUsername(), user);
	}

	@Override
	public User getUserByUsername(String username) {
		if (MapUtils.containsKey(users, username)) {
			return users.get(username);
		}
		return null;
	}

}
