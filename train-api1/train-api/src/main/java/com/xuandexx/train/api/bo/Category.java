package com.xuandexx.train.api.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 主页课程展示:课程分类,课程名列表
 */
@Data
@AllArgsConstructor
public class Category {


    /**
     * 课程排列顺序
     */
    private Integer sortNum;

    /**
     * 课程分类
     */
    private String catalog;

    /**
     * 课程名列表
     */
    private List<Course> courses;
}
