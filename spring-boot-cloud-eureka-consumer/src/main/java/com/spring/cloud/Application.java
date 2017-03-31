package com.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * 通过@EnableDiscoveryClient注解来添加发现服务能力
 * 通过实例化RestTemplate添加@LoadBalanced注解开启均衡负载能力
 * </p>
 * <br/>
 * Date: 2017年3月30日 下午4:28:42 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
