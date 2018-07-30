package com.boot.common.config;

import com.github.pagehelper.PageInterceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.boot.*.dao")
@EnableTransactionManagement
public class DataSourceConfig {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private PageInterceptor pageInterceptor;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		//该配置非常的重要，如果不将PageInterceptor设置到SqlSessionFactoryBean中，导致分页失效
		factoryBean.setPlugins(new Interceptor[]{pageInterceptor});
		factoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.type-aliases-package"));
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybatis.mapper-locations")));
        return factoryBean.getObject();
	}

}
