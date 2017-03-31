package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * 通过@EnableFeignClients注解开启Feign功能
 * </p>
 * <br/>
 * Date: 2017年3月31日 上午10:30:32 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
