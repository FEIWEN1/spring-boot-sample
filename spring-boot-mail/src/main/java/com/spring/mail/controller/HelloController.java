package com.spring.mail.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:HelloController <br/>
 * Title:
 * <p>
 * HelloController
 * </p>
 * <br/>
 * Date: 2017年3月22日 下午4:44:37 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
public class HelloController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello world";
	}

}
