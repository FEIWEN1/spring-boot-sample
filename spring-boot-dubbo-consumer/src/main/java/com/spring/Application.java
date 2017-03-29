package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * </p>
 * <br/>
 * Date: 2017年3月27日 下午4:57:30 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
@ImportResource("classpath:consumer.xml")
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

}
