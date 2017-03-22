package com.spring.mybatis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:ExpressController <br/>
 * Title:
 * <p>
 * ExpressController
 * </p>
 * <br/>
 * Date: 2017年3月21日 下午1:56:48 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
@RequestMapping("express")
public class ExpressController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "hello express";
	}

}
