package com.spring.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mybatis.domain.ExpressCompany;
import com.spring.mybatis.service.ExpressService;


/**
 * ClassName:ExpressController <br/>
 * Title:
 * <p>
 * ExpressController
 * </p>
 * <br/>
 * Date: 2017年3月21日 下午1:56:48 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
@RequestMapping("express")
public class ExpressController {
	
	@Autowired
	private ExpressService expressService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "hello express";
	}
	
	@RequestMapping(value = "/getExpressCompanies", method = RequestMethod.GET)
	public List<ExpressCompany> getExpressCompanies(){
		return expressService.getExpressCompanies();
	}

}
