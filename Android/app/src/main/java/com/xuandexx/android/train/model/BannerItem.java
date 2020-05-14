package com.xuandexx.android.train.model;

import lombok.Data;

/**
 * 浮动广告
 */
public class BannerItem {
    //广告条标题
    private String title;
    //广告条链接
    private String link;
    //广告条封面地址
    private String img;

    private boolean isAd;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isAd() {
        return isAd;
    }

    public void setAd(boolean ad) {
        isAd = ad;
    }
}
