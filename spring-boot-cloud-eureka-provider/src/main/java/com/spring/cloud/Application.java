package com.spring.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * 通过@EnableDiscoveryClient注解能激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出
 * </p>
 * <br/>
 * Date: 2017年3月30日 下午2:51:42 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

}
