package com.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName:ConsumerController <br/>
 * Title:
 * <p>
 * ConsumerController
 * </p>
 * <br/>
 * Date: 2017年3月30日 下午4:41:09 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
public class ConsumerController {

	@Value("${springboot.cloud.server.provider}")
	private String providerAddr;

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public User hello() {
		return restTemplate.getForObject(
				String.format("http://%s/hello", providerAddr), User.class);
	}
}
