package com.spring.mail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.spring.Application;

/**
 * ClassName:MailSendTest <br/>
 * Title:
 * <p>
 * MailSendTest测试用例
 * </p>
 * <br/>
 * Date: 2017年3月22日 下午4:52:39 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class MailSendTest {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Value("${spring.mail.username}")
	private String mailFrom;

	@Value("${mail.to.username}")
	private String mailTo;

	@Test
	@Ignore
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailFrom);
		message.setTo(mailTo);
		message.setSubject("主题：SimpleMailMessage");
		message.setText("测试SimpleMailMessage");
		mailSender.send(message);
	}

	@Test
	@Ignore
	public void sendInlineMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(mailFrom);
		helper.setTo(mailTo);
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:image1\" ></body></html>",
				true);
		FileSystemResource file = new FileSystemResource(new File(
				"C:/win7我的文档-桌面-收藏夹/Desktop/document/picture/images/1.JPEG"));
		helper.addInline("image1", file);
		FileSystemResource attachment1 = new FileSystemResource(new File(
				"C:/win7我的文档-桌面-收藏夹/Desktop/document/picture/images/2.JPEG"));
		helper.addAttachment("附件-1.jpg", attachment1);
		FileSystemResource attachment2 = new FileSystemResource(new File(
				"C:/win7我的文档-桌面-收藏夹/Desktop/document/picture/images/3.JPEG"));
		helper.addAttachment("附件-2.jpg", attachment2);
		mailSender.send(mimeMessage);
	}

	@Test
	public void sendTemplateMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(mailFrom);
		helper.setTo(mailTo);
		helper.setSubject("主题：模板邮件");
		Map<String, Object> model = new HashMap<>();
		model.put("username", "templateMail");
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, "template.vm", "UTF-8", model);
		helper.setText(text, true);
		FileSystemResource file = new FileSystemResource(new File(
				"C:/win7我的文档-桌面-收藏夹/Desktop/document/picture/images/1.JPEG"));
		helper.addAttachment("附件-1.jpg", file);
		mailSender.send(mimeMessage);
	}

}
