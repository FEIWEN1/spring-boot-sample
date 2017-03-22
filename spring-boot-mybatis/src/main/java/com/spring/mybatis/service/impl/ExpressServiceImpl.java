package com.spring.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mybatis.dao.ExpressMapper;
import com.spring.mybatis.domain.ExpressCompany;
import com.spring.mybatis.service.ExpressService;

/**
 * ClassName:ExpressServiceImpl <br/>
 * Title:
 * <p>
 * ExpressService实现
 * </p>
 * <br/>
 * Date: 2017年3月21日 下午2:19:43 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Service
public class ExpressServiceImpl implements ExpressService {

	@Autowired
	private ExpressMapper expressMapper;

	@Override
	public List<ExpressCompany> getExpressCompanies() {
		return expressMapper.getExpressCompanies();
	}

	@Override
	public ExpressCompany getExpressCompanyByNo(String companyNo) {
		return expressMapper.getExpressCompanyByNo(companyNo);
	}

	@Override
	public int addCompany(ExpressCompany company) {
		return expressMapper.addCompany(company);
	}

	@Override
	public int updateCompany(ExpressCompany company) {
		return expressMapper.updateCompany(company);
	}

}
