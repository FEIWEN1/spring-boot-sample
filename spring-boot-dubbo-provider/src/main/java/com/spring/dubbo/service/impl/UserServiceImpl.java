package com.spring.dubbo.service.impl;

import org.springframework.stereotype.Service;

import com.spring.dubbo.bean.User;
import com.spring.dubbo.service.UserService;

/**
 * ClassName:UserServiceImpl <br/>
 * Title:
 * <p>
 * UserService接口实现
 * </p>
 * <br/>
 * Date: 2017年3月27日 下午4:10:53 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public User getUser() {
		User user = new User();
		user.setAge(10);
		user.setUsername("userService");
		return user;
	}

}
