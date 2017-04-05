package com.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:ConfigController <br/>
 * Title:
 * <p>
 * ConfigController
 * </p>
 * <br/>
 * Date: 2017年4月5日 上午10:42:33 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
@RefreshScope
public class ConfigController {

	@Value("${consumer.version:111}")
	private String version;

	@RequestMapping(value = "getConfig", method = RequestMethod.GET)
	public String getConfig() {
		return String.format("version:%s", version);
	}

//	@RequestMapping(value = "/config/refresh", method = RequestMethod.GET)
//	public String refresh() {
//		return new RestTemplate().postForObject("http://localhost:7001/refresh", null, String.class);
//	}

}
