package com.xuandexx.train.api.controller;

import com.ksshop.common.domian.KsResp;
import com.xuandexx.train.api.bo.Advertise;
import com.xuandexx.train.api.bo.Category;
import com.xuandexx.train.api.bo.Course;
import com.xuandexx.train.api.bo.Lesson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("content")
public class ContentController {


    @GetMapping("search")
    public KsResp<String> search(String keyword) {
        return KsResp.success(null);
    }


    @GetMapping("advertiseList")
    public KsResp<List<Advertise>> advertiseList() {
        //课时
        Advertise advertise = new Advertise(1L, "bilibili图片", "www.baidu.com", "www.baidu.com", 1);
        List<Advertise> advertises = new ArrayList<>();
        advertises.add(advertise);
        return KsResp.success(advertises);
    }

    @GetMapping("courseList")
    public KsResp<List<Category>> courseList() {
        //课时
        Lesson lesson = new Lesson(1L, "初级入门第一课", "www.baidu.com");
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        //课程
        Course course = new Course(1L, "初级入门", "作者", "www.baidu.com", lessons);
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        //所有列表
        Category category = new Category(1, "入门课程", courses);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        return KsResp.success(categories);
    }
}
