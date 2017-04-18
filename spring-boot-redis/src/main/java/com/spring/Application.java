package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * @Cacheable 应用到读取数据的方法上，即可缓存的方法，如查找方法：先从缓存中读取，如果没有再调用方法获取数据，然后把数据添加到缓存中
 * @CacheEvict 应用到移除数据的方法上，如删除方法，调用方法时会从缓存中移除相应的数据
 * @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
 * </p>
 * <br/>
 * Date: 2017年4月11日 下午4:50:56 <br/>
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
