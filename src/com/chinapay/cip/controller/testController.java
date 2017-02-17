package com.chinapay.cip.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class testController {

	/**
	 * 测试.
	 * @param session session
	 * @param model model
	 * @return String 
	 */
	@RequestMapping(value = "/test.do")
	@ResponseBody
	public String test(HttpSession session, Model model) {
		return "test";
	}
}
