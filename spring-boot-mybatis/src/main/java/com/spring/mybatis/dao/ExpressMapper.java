package com.spring.mybatis.dao;

import java.util.List;

import com.spring.mybatis.domain.ExpressCompany;

/**
 * ClassName:ExpressMapper <br/>
 * Title:
 * <p>
 * ExpressMapper
 * </p>
 * <br/>
 * Date: 2017年3月21日 下午2:18:24 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public interface ExpressMapper {

	List<ExpressCompany> getExpressCompanies();

	ExpressCompany getExpressCompanyByNo(String companyNo);

	int addCompany(ExpressCompany company);

	int updateCompany(ExpressCompany company);

}
