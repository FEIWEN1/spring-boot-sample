package com.spring.security.service;

import com.spring.security.domain.User;

/**
 * ClassName:UserService <br/>
 * Title:
 * <p>
 * 用户service
 * </p>
 * <br/>
 * Date: 2017年3月29日 下午2:37:53 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public interface UserService {

	User getUserByUsername(String username);

}
