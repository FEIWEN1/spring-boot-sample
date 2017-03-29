package com.spring.dubbo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.Application;
import com.spring.dubbo.bean.User;

/**
 * ClassName:UserServiceTest <br/>
 * Title:
 * <p>
 * UserService测试用例
 * </p>
 * <br/>
 * Date: 2017年3月27日 下午4:20:13 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void getUser() {
		User u = userService.getUser();
		Assert.assertNotNull(u);
	}

}
