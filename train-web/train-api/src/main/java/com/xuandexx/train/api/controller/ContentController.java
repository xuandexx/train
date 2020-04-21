package com.xuandexx.train.api.controller;

import com.ksshop.domain.KsResp;
import com.xuandexx.train.api.bo.Category;
import com.xuandexx.train.api.bo.Course;
import com.xuandexx.train.api.bo.Lesson;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("content")
public class ContentController {


    @GetMapping("search")
    public KsResp<String> search() {
        return KsResp.success(null);
    }

    @GetMapping("courseList")
    public KsResp<List<Category>> list() {
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

    @PutMapping("course/{id}")
    public KsResp<?> update(@PathVariable Long id) {
        return KsResp.success(1);
    }

    @RequestMapping("validate")
    public KsResp<String> validate() {
        return KsResp.success(null);
    }

}
