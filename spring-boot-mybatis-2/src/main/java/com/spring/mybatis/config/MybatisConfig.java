package com.spring.mybatis.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourceArrayPropertyEditor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.util.StringUtils;

/**
 * ClassName:MybatisConfig <br/>
 * Title:
 * <p>
 * mybatis基本配置
 * </p>
 * <br/>
 * Date: 2017年8月23日 下午4:01:39 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
@Configuration
@MapperScan(basePackages = { "com.spring.**.dao" })
public class MybatisConfig implements TransactionManagementConfigurer {

	@Autowired
	DataSource dataSource;
	@Value("${mybatis.config.location:sqlMapperConfig.xml}")
	private String mybatisConfigLocation;

	@Bean(name = { "sqlSessionFactory" })
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(this.dataSource);
//		ResourceArrayPropertyEditor editor = new ResourceArrayPropertyEditor();
//		editor.setValue(null);
//		editor.setAsText("classpath*:com/spring/**/dao123/*Mapper.xml");
//		bean.setMapperLocations((Resource[]) editor.getValue());
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/spring/**/dao123/*Mapper.xml"));
		if (!StringUtils.isEmpty(this.mybatisConfigLocation)) {
			bean.setConfigLocation(new ClassPathResource(
					this.mybatisConfigLocation));
		}
		return bean.getObject();
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(this.dataSource);
	}

}
