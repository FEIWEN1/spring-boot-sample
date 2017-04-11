package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类
 * 交换机的功能主要是接收消息并且转发到绑定的队列，交换机不存储消息，在启用ack模式后，交换机找不到队列会返回错误。
 * 交换机有四种类型：Direct(默认模式), topic, Headers and Fanout
 * Direct：direct 类型的行为是"先匹配, 再投送"(根据key全文匹配去寻找队列). 即在绑定时设定一个 routing_key, 消息的routing_key 匹配时, 才会被交换器投送到绑定的队列中去.
 * Topic：按规则（根据通配符）转发消息,队列和交换机的绑定会定义一种路由模式，那么，通配符就要在这种路由模式和路由键之间匹配后交换机才能转发消息。
 * 		*表示一个词,#表示零个或多个词
 * Headers：设置header attribute参数类型的交换机
 * Fanout：转发消息到所有绑定队列.广播、订阅模式。不管路由键或者是路由模式，会把消息发给绑定给它的全部队列，如果配置了routing_key会被忽略
 * </p>
 * <br/>
 * Date: 2017年4月10日 下午5:11:05 <br/>
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
