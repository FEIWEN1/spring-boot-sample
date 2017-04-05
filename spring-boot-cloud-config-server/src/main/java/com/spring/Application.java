package com.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类 
 * 添加@EnableConfigServer注解，开启Config Server
 * http://localhost:7000/configServer/run/develop
 * application：项目application.name,profile：激活环境,label:对应git分支，默认master
 * 
 * URL与配置文件映射关系：
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 * </p>
 * <br/>
 * Date: 2017年3月31日 下午3:14:37 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
@EnableConfigServer
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

}
