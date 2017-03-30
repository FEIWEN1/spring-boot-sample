package com.spring.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.security.domain.User;
import com.spring.security.service.UserService;

/**
 * ClassName:SecurityUserDetailsService <br/>
 * Title:
 * <p>
 * 通过用户名加载与该用户的用户名、密码以及权限相关的信息
 * </p>
 * <br/>
 * Date: 2017年3月29日 下午2:33:11 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Component
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("当前用户未找到");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), authorities);
	}

}
