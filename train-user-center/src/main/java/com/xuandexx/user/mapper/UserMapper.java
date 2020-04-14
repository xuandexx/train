package com.xuandexx.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuandexx.user.domain.User;

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
