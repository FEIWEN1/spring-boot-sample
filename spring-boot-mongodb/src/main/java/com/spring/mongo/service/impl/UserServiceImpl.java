package com.spring.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.spring.mongo.dao.UserRepository;
import com.spring.mongo.domain.User;
import com.spring.mongo.service.UserService;

/**
 * ClassName:UserServiceImpl <br/>
 * Title:
 * <p>
 * UserService impl
 * </p>
 * <br/>
 * Date: 2017年5月2日 下午2:16:20 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public List<User> getAll() {
		return mongoTemplate.findAll(User.class);
	}

}
