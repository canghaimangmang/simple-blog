package com.example.demo;

import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;
import com.example.demo.util.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wdg on 2018/5/26.
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Value("${rememberMe.key}")
    String rememberKey;


    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


       if(request.getSession().getAttribute("user") != null){
           return true;
       }else{
           Cookie[] cookies =  request.getCookies();
           for(Cookie cookie : cookies){
               if("rememberMe".equals(cookie.getName()) && null !=cookie.getValue()){
                   String value = cookie.getValue();
                   try {


                       String userId = AESUtil.decrypt(value,rememberKey);

                       UserInfo user = userService.findUserById(new Long(userId));

                       if(user != null){
                           request.getSession().setAttribute("user",user);
                           return true;
                       }
                   }catch (Exception e){
                       logger.warn("解析rememberMe Cookie 异常 rememberMe:{},原因:{}",value,e.getMessage());
                   }
               }
           }
       }
       response.sendRedirect(request.getContextPath()+"/login");
       return false;
    }
}
