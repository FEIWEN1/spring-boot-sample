package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName:Application <br/>
 * Title:
 * <p>
 * 启动类.
 * Spring Boot的自动化配置下，模板默认位于resources/templates/目录下
 * 报错1：SMTP协议 身份认证失败(535)
 * org.springframework.mail.MailAuthenticationException: Authentication failed; nested exception is javax.mail.AuthenticationFailedException: 535 Error: ÇëÊ¹ÓÃÊÚÈ¨ÂëµÇÂ¼¡£ÏêÇéÇë¿´
 * 1、检查配置文件填写的发件人的邮箱地址和密码是否正确；2、查看设置host的smtp服务是否已开启(qq邮箱默认不开启)
 * 报错2：501 mail from address must be same as authorization user
 * org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 501 mail from address must be same as authorization user
 * 检查当前发件人和设置的邮件来源是否一致
 * </p>
 * <br/>
 * Date: 2017年3月20日 下午2:39:15 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
