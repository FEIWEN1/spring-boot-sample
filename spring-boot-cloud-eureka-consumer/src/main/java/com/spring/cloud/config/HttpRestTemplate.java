package com.spring.cloud.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName:HttpRestTemplate <br/>
 * Title:
 * <p>
 * 初始化RestTemplate,更改请求编码，并设置http连接池
 * </p>
 * <br/>
 * Date: 2017年3月30日 下午4:32:24 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Configuration
public class HttpRestTemplate {

	@Bean(name = "poolingHttpClientConnectionManager")
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setMaxTotal(250);
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(250);
		return poolingHttpClientConnectionManager;
	}

	@Bean(name = "httpClientBuilder")
	public HttpClientBuilder httpClientBuilder() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder
				.setConnectionManager(poolingHttpClientConnectionManager());
		return httpClientBuilder;
	}

	@Bean(name = "httpClient")
	public HttpClient httpClient() {
		return httpClientBuilder().build();
	}

	@Bean
	public ClientHttpRequestFactory httpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectionRequestTimeout(6000);
		factory.setConnectTimeout(6000);
		factory.setReadTimeout(10000);
		factory.setHttpClient(httpClient());
		return factory;
	}

	@Bean
	public RestTemplate restTemplete() {
		// 添加内容转换器
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converterList = restTemplate
				.getMessageConverters();
		HttpMessageConverter<?> converterTarget = null;
		for (HttpMessageConverter<?> item : converterList) {
			if (item.getClass() == StringHttpMessageConverter.class) {
				converterTarget = item;
				break;
			}
		}
		if (converterTarget != null) {
			converterList.remove(converterTarget);
		}
		HttpMessageConverter<?> converter = new StringHttpMessageConverter(
				StandardCharsets.UTF_8);
		converterList.add(converter);
		restTemplate.setRequestFactory(httpRequestFactory());
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
		return restTemplate;
	}
}
