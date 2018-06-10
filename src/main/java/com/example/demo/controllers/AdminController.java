package com.example.demo.controllers;

import com.example.demo.command.UpdatePwdCommand;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String index(HttpSession session,HttpServletResponse response) throws IOException {

			return "admin/index";

	}
	@RequestMapping("/updatepwd")
	public String updatePwd(){
		return "admin/updatepwd";
	}

	@RequestMapping("/changepwd")
	@ResponseBody
	public Map changePwd(UpdatePwdCommand cmd,HttpSession session){
		Map model = new HashMap();

		if(StringUtils.isEmpty(cmd.getNewpwd()) || StringUtils.isEmpty(cmd.getOldpwd()) || StringUtils.isEmpty(cmd.getConfirmpwd()) ){
			model.put("status",102);
			model.put("message","密码不能为空！");
			return model;

		}else if (!cmd.getNewpwd().equals(cmd.getConfirmpwd())){
			model.put("status",102);
			model.put("message","新密码输入不一致");
			return model;
		}else if (cmd.getNewpwd().equals(cmd.getOldpwd())){
			model.put("status",102);
			model.put("message","新密码不能和原密码一致！");
		}
		UserInfo user  = (UserInfo) session.getAttribute("user");
		if(user != null) {
			user = userService.findUserById(user.getId());
		}else{
			model.put("status",102);
			model.put("message","请重新登陆！");
			return model;
		}
		if(! user.getPassword().equals(cmd.getOldpwd())){
			model.put("status",102);
			model.put("message","原密码不正确！");
			return model;
		}
		user.setPassword(cmd.getNewpwd());
		userService.updatePwd(user);
		session.invalidate();
		return model;
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
