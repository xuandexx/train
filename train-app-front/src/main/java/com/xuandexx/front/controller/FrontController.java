package com.xuandexx.front.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ksshop.common.domian.KsResp;
import com.xuandexx.train.domain.User;

@RestController
@RequestMapping("consuner/user")
public class FrontController {

	public static final String BASE_URL = "http://127.0.0.1:8001";

	public static final String USER_ACCOUNT_URL = BASE_URL + "";

	@Resource
	private RestTemplate restTemplate;

	/**
	 * 用户注册
	 * 
	 * @return
	 */
	@GetMapping("create")
	public KsResp<?> create(User user) {
		return restTemplate.postForObject(BASE_URL + "/user/regist", user, KsResp.class);
	}

	
	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@GetMapping("login")
	public KsResp<String> login() {
		return KsResp.success(null);
	}

	/**
	 * 修改用户信息
	 * 
	 * @return
	 */
	@GetMapping("{id}")
	public KsResp<?> update(@PathVariable Long id) {
		return restTemplate.postForObject(BASE_URL + "/user/1", null, KsResp.class);
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
