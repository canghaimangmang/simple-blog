package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@SpringBootApplication
public class MyBlogApplication implements ServletContextAware{
	@Value("${blog.name}")
	private String blogName;
	public static void main(String[] args) {
		SpringApplication.run(MyBlogApplication.class, args);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setAttribute("blogName",blogName);
	}
}
