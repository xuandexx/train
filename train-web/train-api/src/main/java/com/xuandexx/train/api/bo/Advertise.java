package com.xuandexx.train.api.bo;

import lombok.Data;

/**
 * 首页广告
 */
@Data
public class Advertise {

    /**
     * 课程编号
     */
    private Long pictureId;

    /**
     * 课程名
     */
    private String pictureName;

    /**
     * 图片资源
     */
    private String pictureUri;

    /**
     * 跳转网页
     */
    private String linkUri;

    /**
     * 是否展示
     */
    private Integer state;

}
