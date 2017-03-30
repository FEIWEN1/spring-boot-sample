package com.spring.security.bean;

/**
 * ClassName:Role <br/>
 * Title:
 * <p>
 * user角色，用于权限控制
 * </p>
 * <br/>
 * Date: 2017年3月29日 下午2:41:42 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public enum Role {

	ADMIN("ROLE_admin"),

	USER("user");

	/**
	 * 权限名称
	 */
	private String name;

	Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
