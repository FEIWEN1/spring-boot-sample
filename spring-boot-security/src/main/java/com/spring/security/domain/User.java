package com.spring.security.domain;

import com.spring.security.bean.Role;

/**
 * ClassName:User <br/>
 * Title:
 * <p>
 * 用户权限
 * </p>
 * <br/>
 * Date: 2017年3月29日 下午2:40:07 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public class User {

	/**
	 * 用户名称
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * {@link Role}
	 */
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
