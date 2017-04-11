package com.spring.rabbit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.Application;
import com.spring.rabbitmq.service.RabbitService;

/**
 * ClassName:RabbitServiceTest <br/>
 * Title:
 * <p>
 * RabbitService测试用例
 * </p>
 * <br/>
 * Date: 2017年4月10日 下午5:43:58 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RabbitServiceTest {

	@Autowired
	private RabbitService rabbitService;

	@Test
	public void directTest() {
		rabbitService.directSend();
	}

	@Test
	public void topicTest() {
		rabbitService.topicSend();
	}

	@Test
	public void fanoutTest() {
		rabbitService.fanoutSend();
	}

}
