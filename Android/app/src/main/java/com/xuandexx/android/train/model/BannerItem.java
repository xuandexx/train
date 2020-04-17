package com.xuandexx.android.train.model;

import lombok.Data;

/**
 * 浮动广告
 */
@Data
public class BannerItem {
    //广告条标题
    private String title;
    //广告条链接
    private String link;
    //广告条封面地址
    private String img;

    private boolean isAd;
}
