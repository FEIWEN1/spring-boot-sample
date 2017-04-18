package com.spring.redis.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:User <br/>
 * Title:
 * <p>
 * User
 * </p>
 * <br/>
 * Date: 2017年4月11日 下午5:20:20 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 创建时间
	 */
	private Date createTime;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
