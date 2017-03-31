package com.spring.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hystrix.bean.User;
import com.spring.hystrix.service.HystrixConsumerService;

/**
 * ClassName:ConsumerController <br/>
 * Title:
 * <p>
 * ConsumerController
 * </p>
 * <br/>
 * Date: 2017年3月31日 上午11:17:30 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
public class ConsumerController {
	
	@Autowired
	private HystrixConsumerService consumerService;
	
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public User hello() {
		return consumerService.helloService();
	}

}
