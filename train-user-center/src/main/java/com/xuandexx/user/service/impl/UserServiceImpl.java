package com.xuandexx.user.service.impl;

import com.xuandexx.user.domain.User;
import com.xuandexx.user.mapper.UserMapper;
import com.xuandexx.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
