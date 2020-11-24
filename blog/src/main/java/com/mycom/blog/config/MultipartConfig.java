package com.mycom.blog.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Component
public class MultipartConfig {

	@Bean
	javax.servlet.MultipartConfigElement MultipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();

	    DataSize ds = DataSize.ofMegabytes(1);
	    factory.setMaxFileSize(ds);
	    factory.setMaxRequestSize(ds);

	    return factory.createMultipartConfig();
	}

	@Bean
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
}
