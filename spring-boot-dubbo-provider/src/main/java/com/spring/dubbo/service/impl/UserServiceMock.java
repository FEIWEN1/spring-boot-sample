package com.spring.dubbo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.spring.dubbo.bean.User;
import com.spring.dubbo.service.UserService;

/** 
 * ClassName:UserServiceMock <br/> 
 * Title: <p>TODO</p> <br/> 
 * Date:     2017年3月27日 下午5:52:11 <br/> 
 * @author   shichao
 * @version 
 * 
 */
@Service("userServiceMock")
@Primary
public class UserServiceMock implements UserService {

	@Override
	public User getUser() {
		User user = new User();
		user.setAge(100);
		user.setUsername("userServiceMock");
		return user;
	}

}
