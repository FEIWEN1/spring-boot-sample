package com.spring.rabbitmq.service;

/**
 * ClassName:RabbitService <br/>
 * Title:
 * <p>
 * Rabbit4种模式调用方法
 * </p>
 * <br/>
 * Date: 2017年4月10日 下午5:30:42 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public interface RabbitService {

	/**
	 * 
	 * @Title:<p>Direct模式调用</p><br/>
	 * @author shichao
	 */
	void directSend();

	/**
	 * 
	 * @Title:<p>Topic模式调用</p><br/>
	 * @author shichao
	 */
	void topicSend();

	/**
	 * 
	 * @Title:<p>Headers模式调用</p><br/>
	 * @author shichao
	 */
	void headersSend();

	/**
	 * 
	 * @Title:<p>Fanout模式调用</p><br/>
	 * @author shichao
	 */
	void fanoutSend();

}
