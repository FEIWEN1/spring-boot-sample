package com.spring.mybatis.dao;

import java.util.List;

import com.spring.mybatis.domain.ExpressCompany;

/**
 * ClassName:ExpressCompanyMapper <br/>
 * Title:
 * <p>
 * ExpressCompanyMapper
 * </p>
 * <br/>
 * Date: 2017年8月23日 下午3:28:43 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public interface ExpressCompanyMapper {

	/**
	 * 
	 * @Title:<p>获取快递公司列表</p><br/>
	 * @author shichao
	 * @return
	 */
	List<ExpressCompany> queryAll();

}
