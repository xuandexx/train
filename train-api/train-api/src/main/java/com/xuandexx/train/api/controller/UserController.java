package com.xuandexx.train.api.controller;


import com.ksshop.common.domian.KsResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Simon
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

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
    @GetMapping("{id}")
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

