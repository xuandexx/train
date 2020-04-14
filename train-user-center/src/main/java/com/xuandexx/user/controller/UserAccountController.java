package com.xuandexx.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksshop.common.domian.KsResp;

@RestController
@RequestMapping("user")
public class UserAccountController {

	/**
	 * 用户注册
	 * 
	 * @return
	 */
	@RequestMapping("regist")
	public KsResp<String> regist() {
		return KsResp.success(null);
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public KsResp<String> login() {
		return KsResp.success(null);
	}

	/**
	 * 修改用户信息
	 * 
	 * @return
	 */
	@PutMapping("{id}")
	public KsResp<?> update(@PathVariable Long id) {
		return KsResp.success(1);
	}

	/**
	 * 用户获取验证码
	 * 
	 * @return
	 */
	@RequestMapping("validate")
	public KsResp<String> validate() {
		return KsResp.success(null);
	}

}
