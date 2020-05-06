package com.xuandexx.android.train.model;

import lombok.Data;

/**
 * 课程
 */
@Data
public class Course {
    /**
     * 封面
     */
    private String cover;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程数量
     */
    private Integer lessionNum;
    /**
     * 课程评论数
     */
    private Integer forks;
}