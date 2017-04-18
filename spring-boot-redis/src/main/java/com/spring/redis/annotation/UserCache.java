package com.spring.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

/**
 * ClassName:UserCache <br/>
 * Title:
 * <p>
 * 自定义注解把@Caching组合注解组合到一个注解中
 * </p>
 * <br/>
 * Date: 2017年4月17日 下午4:42:27 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Caching(put = { @CachePut(value = "user", key = "#user.mobile"),
		@CachePut(value = "user", key = "#user.username") })
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UserCache {

}
