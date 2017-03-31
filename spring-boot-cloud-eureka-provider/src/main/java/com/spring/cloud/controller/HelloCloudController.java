package com.spring.cloud.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.bean.User;

/**
 * ClassName:HelloCloudController <br/>
 * Title:
 * <p>
 * HelloCloudController
 * </p>
 * <br/>
 * Date: 2017年3月30日 下午2:54:31 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
public class HelloCloudController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HelloCloudController.class);

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public User helloCloud() {
		ServiceInstance instance = client.getLocalServiceInstance();
		LOGGER.info("===========>host:{},service_id:{}", instance.getHost(),
				instance.getServiceId());
		User u = new User();
		u.setUsername("shichao");
		u.setBirthday(new Date());
		return u;
	}

}
