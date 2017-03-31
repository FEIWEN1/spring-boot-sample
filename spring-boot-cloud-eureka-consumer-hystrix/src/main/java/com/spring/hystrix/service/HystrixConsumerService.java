package com.spring.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.hystrix.bean.User;

/**
 * ClassName:HystrixConsumerService <br/>
 * Title:
 * <p>
 * HystrixConsumerService
 * </p>
 * <br/>
 * Date: 2017年3月31日 上午11:14:25 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Service
public class HystrixConsumerService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${springboot.cloud.server.provider}")
	private String providerAddr;
	
	@HystrixCommand(fallbackMethod = "helloServiceFallback")
	public User helloService() {
		return restTemplate.getForObject(
				String.format("http://%s/hello", providerAddr), User.class);
	}

	public User helloServiceFallback() {
		return null;
	}
}
