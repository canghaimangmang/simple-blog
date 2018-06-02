package com.example.demo;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public FilterRegistrationBean<Filter> sitemeshFilter(){
		FilterRegistrationBean filter = new FilterRegistrationBean<>();
		filter.setFilter(new MySiteMeshFilter());
		return filter;
	}
}
