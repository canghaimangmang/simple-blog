package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

     //   registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");

        registry.addInterceptor(userInterceptor).addPathPatterns("/admin/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        DateFormatter dateFormatter = new DateFormatter();
//        dateFormatter.setPattern("yyyy-MM-dd hh:mm:ss");
//        dateFormatter.setLenient(true);
//
//        DateFormatter dateFormatter2 = new DateFormatter();
//        dateFormatter2.setPattern("yyyy-MM-dd");
//        dateFormatter2.setLenient(true);
//        registry.addFormatterForFieldType(Date.class,dateFormatter2);
        registry.addFormatterForFieldType(Date.class,new DateFormatter(){
            @Override
            public Date parse(String text, Locale locale) throws ParseException {
                try{
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text);
                }catch (Exception e){
                    return new SimpleDateFormat("yyyy-MM-dd").parse(text);
                }
            }
        });
    }
}