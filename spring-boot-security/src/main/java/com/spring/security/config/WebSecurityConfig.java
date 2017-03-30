package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * ClassName:WebSecurityConfig <br/>
 * Title:
 * <p>
 * 配置WebSecurityConfigurerAdapter 1、通过 @EnableWebMvcSecurity 注解开启Spring
 * Security的功能 2、继承 WebSecurityConfigurerAdapter ，并重写它的方法来设置一些web安全的细节
 * 3、configure(HttpSecurity http) 方法 通过 authorizeRequests()
 * 定义哪些URL需要被保护、哪些不需要被保护。例如以上代码指定了 / 和 /home 不需要任何认证就可以访问，其他的路径都必须通过身份验证。 通过
 * formLogin() 定义当需要用户登录时候，转到的登录页面。
 * 4、configureGlobal(AuthenticationManagerBuilder auth)
 * 方法，在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER。
 * 5、使用@EnableGlobalMethodSecurity(prePostEnabled =
 * true)这个注解，可以开启security的注解，我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解
 * </p>
 * <br/>
 * Date: 2017年3月29日 下午1:55:47 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		return new SecurityUserDetailsService();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		// 在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER
		// auth.inMemoryAuthentication().withUser("user").password("password")
		// .roles("USER");
		auth.userDetailsService(userDetailsService()).passwordEncoder(
				passwordEncoder());
	}

	/**
	 * 设置用户密码的加密方式为MD5加密
	 * 
	 * @return
	 */
	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder;

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout()
				.logoutSuccessUrl("/login").permitAll();
	}

}
