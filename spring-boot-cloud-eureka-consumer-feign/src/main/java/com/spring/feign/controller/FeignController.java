package com.spring.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.feign.service.FeignConsumerService;

/**
 * ClassName:FeignController <br/>
 * Title:
 * <p>
 * FeignController
 * </p>
 * <br/>
 * Date: 2017年3月31日 上午10:37:41 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
public class FeignController {

	@Autowired
	private FeignConsumerService feignConsumerService;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello() {
		return feignConsumerService.hello();
	}

}
