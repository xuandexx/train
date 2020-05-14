package com.xuandexx.train.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
