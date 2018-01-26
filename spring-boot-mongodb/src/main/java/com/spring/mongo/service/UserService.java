package com.spring.mongo.service;

import java.util.List;

import com.spring.mongo.domain.User;

/**
 * ClassName:UserService <br/>
 * Title:
 * <p>
 * UserService
 * </p>
 * <br/>
 * Date: 2017年5月2日 下午2:15:36 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public interface UserService {

	/**
	 * 
	 * @Title:<p>添加或修改用户</p><br/>
	 * @author shichao
	 * @param user
	 * @return
	 */
	User saveUser(User user);

	/**
	 * 
	 * @Title:<p>获取所有会员列表</p><br/>
	 * @author shichao
	 * @return
	 */
	List<User> findAll();

	/**
	 * 
	 * @Title:<p>通过用户名查找</p><br/>
	 * @author shichao
	 * @param username
	 * @return
	 */
	List<User> findByUsername(String username);

	/**
	 * 
	 * @Title:<p>删除会员</p><br/>
	 * @author shichao
	 * @param user
	 */
	void deleteUser(User user);
	
	List<User> getAll();

}
