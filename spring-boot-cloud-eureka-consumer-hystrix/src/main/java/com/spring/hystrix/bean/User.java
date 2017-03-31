package com.spring.hystrix.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:User <br/>
 * Title:
 * <p>
 * User
 * </p>
 * <br/>
 * Date: 2017年3月30日 下午5:34:03 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	private String username;

	private Date birthday;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
