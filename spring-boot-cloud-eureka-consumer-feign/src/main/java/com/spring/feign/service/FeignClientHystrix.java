package com.spring.feign.service;

import org.springframework.stereotype.Component;

/**
 * ClassName:FeignClientHystrix <br/>
 * Title:
 * <p>
 * 回调类
 * </p>
 * <br/>
 * Date: 2017年3月31日 下午2:38:41 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Component
public class FeignClientHystrix implements FeignConsumerService {

	@Override
	public String hello() {
		return "FeignError";
	}

}
