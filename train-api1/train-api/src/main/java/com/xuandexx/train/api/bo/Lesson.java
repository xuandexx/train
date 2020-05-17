package com.xuandexx.train.api.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 课时分类
 */
@Data
public class Lesson {
    /**
     * 课时编号
     */
    private Long lessonId;

    /**
     * 课时要点
     */
    private String detail;

    /**
     * 所属课程id
     */
    private Long courseId;

    /**
     * 课程视频地址
     */
    private String uri;

    public Lesson(Long lessonId, String detail, String uri) {
        this.lessonId = lessonId;
        this.detail = detail;
        this.uri = uri;
    }
}