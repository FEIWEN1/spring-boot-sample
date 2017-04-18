package com.spring.session.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * ClassName:SessionConfig <br/>
 * Title:
 * <p>
 * session配置
 * maxInactiveIntervalInSeconds: 设置Session失效时间，使用Redis Session之后，原Boot的server.session.timeout属性不再生效
 * </p>
 * <br/>
 * Date: 2017年4月18日 下午2:32:22 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class SessionConfig {

}
