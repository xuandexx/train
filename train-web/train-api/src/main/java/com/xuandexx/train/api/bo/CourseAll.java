package com.xuandexx.train.api.bo;

import lombok.Data;

import java.util.List;

/**
 * 主页课程展示:课程分类,课程名列表
 */
@Data
public class CourseAll {


    /**
     * 课程排列顺序
     */
    private Integer sortNum;

    /**
     * 课程分类
     */
    private String courseCatalog;

    /**
     * 课程封面
     */
    private String uri;

    /**
     * 课程名列表
     */
    private List<String> courseName;
}
