package com.huashitu.service.indexPage.impl;

import com.huashitu.config.BaseServiceImpl;
import com.huashitu.domain.FansAttention;
import com.huashitu.domain.User;
import com.huashitu.mapper.FansAttentionMapper;
import com.huashitu.service.indexPage.IFansAttentionService;
import com.huashitu.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by levy on 2016/11/21.
 */

@Service
public class FansAttentionServiceImpl extends BaseServiceImpl<FansAttention> implements IFansAttentionService {

    @Autowired
    private FansAttentionMapper fansAttentionMapper;

    @Override
    protected BaseMapper<FansAttention> getBaseMapper() {
        return this.fansAttentionMapper;
    }

    @Override
    public List<User> selectIdolByUserId(Long userId) {
        return this.fansAttentionMapper.selectIdolByUserId(userId);
    }
}
