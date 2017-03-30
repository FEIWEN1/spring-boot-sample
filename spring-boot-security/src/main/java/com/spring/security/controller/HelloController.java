package com.spring.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:HelloController <br/>
 * Title:
 * <p>
 * HelloController
 * </p>
 * <br/>
 * Date: 2017年3月29日 下午2:13:02 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Controller
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/hello")
	@PreAuthorize("hasAnyRole('ROLE_admin')")
	public String hello() {
		return "hello";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/403")
	public String forbidden() {
		return "forbidden";
	}

}
