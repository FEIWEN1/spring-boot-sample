package com.spring.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName:FeignConsumerService <br/>
 * Title:
 * <p>
 * 使用@FeignClient("spring-cloud-eureka-provider")注解来绑定该接口对应服务 
 * 通过SpringMVC的注解来配置spring-cloud-eureka-provider服务下的具体实现
 * </p>
 * <br/>
 * Date: 2017年3月31日 上午10:33:24 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@FeignClient("spring-cloud-eureka-provider")
public interface FeignConsumerService {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	String hello();

}
