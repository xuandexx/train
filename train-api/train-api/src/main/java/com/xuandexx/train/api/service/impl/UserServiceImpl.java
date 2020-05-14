package com.xuandexx.train.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xuandexx.train.domain.User;
import com.xuandexx.train.mapper.UserMapper;
import com.xuandexx.train.service.IUserService;
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
