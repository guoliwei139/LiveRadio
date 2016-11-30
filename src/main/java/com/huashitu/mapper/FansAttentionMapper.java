package com.huashitu.mapper;

import com.huashitu.domain.FansAttention;
import com.huashitu.domain.User;
import com.huashitu.util.BaseMapper;

import java.util.List;

public interface FansAttentionMapper extends BaseMapper<FansAttention> {
    public List<User> selectIdolByUserId(Long userId);

}
