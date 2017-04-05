package com.spring.cloud;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.spring.cloud.filter.AccessFilter;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * </p>
 * <br/>
 * Date: 2017年4月5日 下午3:02:00 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@EnableZuulProxy
@SpringCloudApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
	
}
