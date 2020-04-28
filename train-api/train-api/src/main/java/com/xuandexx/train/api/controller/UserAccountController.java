package com.xuandexx.train.api.controller;

import com.ksshop.domain.KsResp;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/account")
public class UserAccountController {


    /**
     * 用户注册
     *
     * @return
     */
    @GetMapping("create")
    public KsResp<String> create() {
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
     *
     *
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
