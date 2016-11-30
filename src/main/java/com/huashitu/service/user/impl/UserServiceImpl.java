package com.huashitu.service.user.impl;

import com.huashitu.config.BaseServiceImpl;
import com.huashitu.domain.User;
import com.huashitu.mapper.UserMapper;
import com.huashitu.service.user.IUserService;
import com.huashitu.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by levy on 2016/11/7.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long insertUser(User user) {
        return this.userMapper.insertUser(user);
    }

    @Override
    protected BaseMapper<User> getBaseMapper() {
        return this.userMapper;
    }
}
