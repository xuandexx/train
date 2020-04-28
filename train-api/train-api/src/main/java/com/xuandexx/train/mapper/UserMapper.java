package com.xuandexx.train.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xuandexx.train.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Simon
 * @since 2020-04-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
