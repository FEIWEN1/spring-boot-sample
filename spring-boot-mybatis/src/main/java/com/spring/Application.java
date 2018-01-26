package com.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类,mybatis映射的domain类必须有空的构造方法.
 * Maven部署Web项目报错webxml attribute is required
 * maven的web项目默认的webroot是在src\main\webapp。如果在此目录下找不到web.xml就抛出以上的异常
 * 需要在pom.xml中增加<webResources>配置<webXml>webapp\WEB-INF\web.xml</webXml>或者 <failOnMissingWebXml>false</failOnMissingWebXml>
 * <!-- 
 *	prefix：前缀覆盖并增加其内容
 *	suffix：后缀覆盖并增加其内容
 *	prefixOverrides：前缀判断的条件
 *	suffixOverrides：后缀判断的条件
 *	 -->
 * mybatis There is no getter for property named 'xxxx' 检查参数
 * </p>
 * <br/>
 * Date: 2017年3月20日 下午2:39:15 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
//mybatis mapper 扫描
@MapperScan({ "com.spring.*.dao" })
public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

}
