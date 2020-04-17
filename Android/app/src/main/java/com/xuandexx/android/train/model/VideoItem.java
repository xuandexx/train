package com.xuandexx.android.train.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class VideoItem implements Serializable {
    private String aid;//视频av号
    private String typeid;//视频类型
    private String title;//视频标题
    private String sbutitle;
    private String play;//视频播放数
    private String review;//评论数
    private String video_review;//视频弹幕数
    private String favorites;//视频收藏数
    private String mid;
    private String author;//Up主
    private String description;//视频简介
    private String create;//视频发布时间
    private String pic;//视频封面地址
    private String credit;
    private String coins;//视频硬币数
    private String duration;//视频长度


}
