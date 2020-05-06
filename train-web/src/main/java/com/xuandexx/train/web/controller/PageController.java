package com.xuandexx.train.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网站后台页面
 * 
 * @author Simon
 */
@Controller
public class PageController {

	/*
	 * 登录页面
	 */
	@RequestMapping({ "/", "/login" })
	public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "/login";
	}



}