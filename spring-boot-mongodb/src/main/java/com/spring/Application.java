package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName:Appliaction <br/>
 * Title:
 * <p>
 * 启动类,如当前主键id不设置，由框架自动生成，则将主键设置成String，设置成Long等则需要对主键设值
 * </p>
 * <br/>
 * Date: 2017年5月2日 下午2:03:57 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
