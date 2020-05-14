package com.xuandexx.train.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuandexx.train.api.mapper.UserMapper;
import com.xuandexx.train.api.service.IUserService;
import com.xuandexx.train.domain.User;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Simon
 * @since 2020-04-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
