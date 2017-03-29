package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * bean必须序列化，接口实现必须添加spring id以便找到其bean
 * </p>
 * <br/>
 * Date: 2017年3月27日 下午3:26:42 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
@ImportResource("classpath:provider.xml")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
