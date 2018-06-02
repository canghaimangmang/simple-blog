package com.example.demo.util;

import com.example.demo.model.UserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by wdg on 2018/6/2.
 */
public class AdminUtils {

    public static UserInfo getUserInfo(){

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession req = sra.getRequest().getSession();
        UserInfo user = (UserInfo) req.getAttribute("user");
        return user;
    }
}
