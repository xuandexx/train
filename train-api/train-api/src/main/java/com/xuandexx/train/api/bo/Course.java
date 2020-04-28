package com.xuandexx.train.api.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 具体课程
 */
@Data
@AllArgsConstructor
public class Course {

    /**
     * 课程编号
     */
    private Long courseId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程作者
     */
    private String author;

    /**
     * 课程封面
     */
    private String uri;

    /**
     * 课程名列表
     */
    private List<Lesson> lessons;
}
