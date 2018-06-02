package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.command.LoginCommand;
import com.example.demo.model.Article;
import com.example.demo.model.UserInfo;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;

	@Value("${rememberMe.key}")
	private String rememberKey;
	
	@RequestMapping("/bloghome")
	public String blogHome(HttpServletRequest request,Model model) {
		
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		
		List<Article> articleList = articleService.queryArticle(userId);
		
		model.addAttribute("articleList", articleList);
		
		return "home";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map login(HttpServletRequest request, HttpServletResponse response, LoginCommand cmd) {
		Map map = new HashMap();
		
		UserInfo user =  userService.findUser(cmd.getUsername());
		
		if(user==null) {
			map.put("status", 101);
			map.put("message", "用户不存在");
		}else if(!user.getPassword().equals(cmd.getPassword())) {
			map.put("status", 102);
			map.put("message", "密码不正确");
		}else {
			request.getSession().setAttribute("user", user);
			if("on".equals(cmd.getRememberMe())){
				String val = AESUtil.encrypt(user.getId().toString(),rememberKey);
				Cookie cookie = new Cookie("rememberMe",val);
				cookie.setMaxAge(3600*24*7);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		return map;
	}
	
}
