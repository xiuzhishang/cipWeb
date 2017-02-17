package com.chinapay.cip.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinapay.cip.model.AppUser;
import com.chinapay.cip.service.AppUserService;
@Controller
@RequestMapping(value="/login")
public class loginController {

	@Autowired
	private AppUserService appUserService;
	/**
	 * 登录.
	 * @return String 
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request ,Model model) {
		String mid = request.getParameter("mid")==null?"":request.getParameter("mid");
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String pwd = request.getParameter("pwd")==null?"":request.getParameter("pwd");
		Map map = appUserService.vilidateUser(mid,id,pwd,"1");
		String str = (String)map.get("message");
		if (!"OK".equals(str)) {
			model.addAttribute("message", str);
			return "result";
		}
		AppUser user = (AppUser)map.get("user");
		model.addAttribute("user", user);
		return "result";
	}
}
