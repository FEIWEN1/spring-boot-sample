package com.spring.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.mongo.domain.User;

/**
 * ClassName:UserRepository <br/>
 * Title:
 * <p>
 * UserRepository
 * </p>
 * <br/>
 * Date: 2017年5月2日 下午2:08:55 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public interface UserRepository extends MongoRepository<User, Long> {

	List<User> findByUsername(String username);
	
}
