package com.spring.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * 通过@EnableEurekaServer注解启动一个服务注册中心提供给其他应用进行对话
 * 在默认设置下，该服务注册中心也会将自己作为客户端来尝试注册它自己，需要禁用它的客户端注册行为，只需要在application.properties中问增加配置
 * </p>
 * <br/>
 * Date: 2017年3月30日 下午2:32:36 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
@EnableEurekaServer
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

}
