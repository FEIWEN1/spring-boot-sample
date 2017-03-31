package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 使用@EnableCircuitBreaker注解开启断路器功能
 * </p>
 * <br/>
 * Date: 2017年3月31日 上午11:11:21 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
