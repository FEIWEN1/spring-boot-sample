package com.spring.security.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * ClassName:ErrorConfig <br/>
 * Title:
 * <p>
 * TODO
 * </p>
 * <br/>
 * Date: 2017年3月29日 下午4:49:24 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Configuration
public class ErrorConfig {

	@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
		return new MyCustomizer();
	}

	private static class MyCustomizer implements
			EmbeddedServletContainerCustomizer {

		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container
					.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
		}

	}
}
