package com.example.demo;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter{
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/*", "/jsp/decoration.jsp")
		.addExcludedPath("/admin/*")
		.addExcludedPath("/static/*")
		.addExcludedPath("/jsp/admin/ueditor/*");
	}
}
