package com.example.demo;

import javax.servlet.Filter;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class AppConfig {
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Bean
	public FilterRegistrationBean<Filter> sitemeshFilter(){
		FilterRegistrationBean filter = new FilterRegistrationBean<>();
		filter.setFilter(new MySiteMeshFilter());
		return filter;
	}

	@Bean
	public DataSource dataSource() throws SQLException {
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl+"&useUnicode=true&characterEncoding=UTF-8");

			config.setDriverClassName(driverClassName);
			return new HikariDataSource(config);
		}
	}

}
