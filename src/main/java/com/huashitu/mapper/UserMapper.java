package com.huashitu.mapper;

import com.huashitu.domain.User;
import com.huashitu.util.BaseMapper;

public interface UserMapper extends BaseMapper<User> {
    Long insertUser(User user);
}