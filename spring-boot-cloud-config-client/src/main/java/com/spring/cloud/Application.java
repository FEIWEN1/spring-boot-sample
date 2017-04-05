package com.spring.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * 因为config的相关配置会先于application.properties，而bootstrap.properties的加载也是先于application.properties。
 * 所以属性必须配置在bootstrap.properties中，config部分内容才能被正确加载
 * 注解@RefreshScope指示Config客户端在服务器参数刷新时，也刷新注入的属性值
 * /refresh动态刷新配置
 * </p>
 * <br/>
 * Date: 2017年4月5日 上午10:33:07 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
