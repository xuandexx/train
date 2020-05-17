package com.xuandexx.train.api.controller;

import com.ksshop.common.domian.KsResp;
import com.xuandexx.train.api.bo.Broadcast;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询历史
 */
@RestController
@RequestMapping("history")
public class HistoryController {

    @GetMapping("broadcast")
    public KsResp<List<Broadcast>> broadcastHistory(String keyword) {
        Broadcast broadcast = new Broadcast(1L, 1L, 1L, 2000L);
        List<Broadcast> broadcasts = new ArrayList<>();
        broadcasts.add(broadcast);
        return KsResp.success(broadcasts);
    }

    @GetMapping("buy")
    public KsResp<List<Broadcast>> buyHistory(String keyword) {
        Broadcast broadcast = new Broadcast(1L, 1L, 1L, 2000L);
        List<Broadcast> broadcasts = new ArrayList<>();
        broadcasts.add(broadcast);
        return KsResp.success(broadcasts);
    }
}