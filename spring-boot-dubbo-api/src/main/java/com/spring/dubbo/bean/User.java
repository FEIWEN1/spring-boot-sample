package com.spring.dubbo.bean;

import java.io.Serializable;

/**
 * ClassName:User <br/>
 * Title:
 * <p>
 * 用户bean
 * </p>
 * <br/>
 * Date: 2017年3月27日 下午3:20:02 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	private Integer age;

	private String username;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
