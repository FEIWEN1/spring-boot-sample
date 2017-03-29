package com.spring.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dubbo.bean.User;
import com.spring.dubbo.service.UserService;

/**
 * ClassName:UserController <br/>
 * Title:
 * <p>
 * UserController
 * </p>
 * <br/>
 * Date: 2017年3月27日 下午5:01:15 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
@RequestMapping("dubbo")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	public User getUser() {
		return userService.getUser();
	}

}
