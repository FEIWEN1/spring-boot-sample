package com.spring.conf;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置类
 *
 * @author shichao
 * @date 2018/6/29 上午11:21
 */
@Configuration
public class ThreadExecutorConf {


    @Bean
    public ThreadPoolTaskExecutor orderTopicExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(20);
        executor.setCorePoolSize(5);
        return executor;
    }

}
