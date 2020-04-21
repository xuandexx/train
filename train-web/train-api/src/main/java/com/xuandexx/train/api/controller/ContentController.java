package com.xuandexx.train.api.controller;

import com.ksshop.domain.KsResp;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("content")
public class ContentController {


    @GetMapping("search")
    public KsResp<String> search() {
        return KsResp.success(null);
    }

    @GetMapping("courseList")
    public KsResp<String> list() {
        return KsResp.success(null);
    }

    @PutMapping("course/{id}")
    public KsResp<?> update(@PathVariable Long id) {
        return KsResp.success(1);
    }

    @RequestMapping("validate")
    public KsResp<String> validate() {
        return KsResp.success(null);
    }

}
