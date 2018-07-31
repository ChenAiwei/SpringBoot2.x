package com.boot.common.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 容器配置文件 可XML配置
 * 指定端口、session时间等等
 * @author aiwei
 *
 */
@Configuration
public class CustomServletContainer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		// TODO Auto-generated method stub
	//	factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/pages/404.html"));
	}

}
