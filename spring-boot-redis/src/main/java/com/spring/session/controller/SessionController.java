package com.spring.session.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:SessionController <br/>
 * Title:
 * <p>
 * SessionController
 * </p>
 * <br/>
 * Date: 2017年4月18日 下午2:32:33 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RestController
public class SessionController {

	@RequestMapping(value = "/getSessionId", method = RequestMethod.GET)
	public String getSessionId(HttpSession session) {
		return session.getId();
	}

}
