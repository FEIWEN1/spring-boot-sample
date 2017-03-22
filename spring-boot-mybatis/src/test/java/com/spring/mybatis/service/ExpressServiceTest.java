package com.spring.mybatis.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.Application;
import com.spring.mybatis.domain.ExpressCompany;

/**
 * ClassName:ExpressServiceTest <br/>
 * Title:
 * <p>
 * ExpressServiceTest
 * </p>
 * <br/>
 * Date: 2017年3月21日 下午3:11:08 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ExpressServiceTest {

	@Autowired
	private ExpressService expressService;

	@Test
	public void getExpressCompanies() {
		List<ExpressCompany> companies = expressService.getExpressCompanies();
		Assert.assertNotNull(companies);
	}
	
	@Test
	public void getExpressCompanyByNo(){
		String companyNo = "yuantong";
		ExpressCompany company = expressService.getExpressCompanyByNo(companyNo);
		Assert.assertNotNull(company);
		
	}
	
	@Test
	@Ignore
	public void addCompany(){
		ExpressCompany company = new ExpressCompany();
		company.setCompany("ceshi");
		company.setCompanyNo("aaaaa");
		int result = expressService.addCompany(company);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void updateCompany(){
		ExpressCompany company = new ExpressCompany();
//		company.setId(1L);
		company.setCompanyNo("aaaaa");
		company.setCompany("ddddasdsadada");
		int result = expressService.updateCompany(company);
		Assert.assertEquals(1, result);
	}

}
