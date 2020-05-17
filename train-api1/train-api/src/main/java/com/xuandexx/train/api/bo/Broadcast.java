package com.xuandexx.train.api.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 播放历史
 */
@Data
@AllArgsConstructor
public class Broadcast {

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 课时id
     */
    private Long lessonId;

    /**
     * 历史播放时间点
     */
    private Long broadcastPoint;

}
