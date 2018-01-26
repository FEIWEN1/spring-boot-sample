package com.spring.mybatis.domain;

import java.io.Serializable;

/**
 * ClassName:Express <br/>
 * Title:
 * <p>
 * 物流公司数据库bean
 * </p>
 * <br/>
 * Date: 2017年3月21日 下午2:20:26 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SuppressWarnings("serial")
public class ExpressCompany implements Serializable {

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 承运商名称
	 */
	private String company;
	/**
	 * 承运商编号
	 */
	private String companyNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

}
