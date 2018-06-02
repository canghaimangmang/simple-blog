package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/index")
	public String index(HttpSession session,HttpServletResponse response) throws IOException {

			return "admin/index";

	}
	@RequestMapping("/logout")
	public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
			for(Cookie cookie:request.getCookies()){
				if("rememberMe".equals(cookie.getName())){
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}

			session.invalidate();



			return "redirect:/login";

	}
}
