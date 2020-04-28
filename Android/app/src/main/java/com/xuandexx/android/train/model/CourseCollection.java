package com.xuandexx.android.train.model;

import java.util.List;

import lombok.Data;

/**
 * 内容课程列表
 */
@Data
public class CourseCollection {

    private String title;

    private List<Course> list;

}
