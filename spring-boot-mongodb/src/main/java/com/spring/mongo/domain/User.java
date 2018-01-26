package com.spring.mongo.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * ClassName:User <br/>
 * Title:
 * <p>
 * user数据库实例
 * </p>
 * <br/>
 * Date: 2017年5月2日 下午2:05:54 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public class User {

	@Id
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 生日
	 */
	private Date birthday;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
