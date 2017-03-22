package com.spring.mybatis.service;

import java.util.List;

import com.spring.mybatis.domain.ExpressCompany;

/**
 * ClassName:ExpressService <br/>
 * Title:
 * <p>
 * ExpressService
 * </p>
 * <br/>
 * Date: 2017年3月21日 下午2:19:27 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public interface ExpressService {

	List<ExpressCompany> getExpressCompanies();

	ExpressCompany getExpressCompanyByNo(String companyNo);
	
	int addCompany(ExpressCompany company);
	
	int updateCompany(ExpressCompany company);

}
