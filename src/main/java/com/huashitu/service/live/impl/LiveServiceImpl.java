package com.huashitu.service.live.impl;

import com.huashitu.config.BaseServiceImpl;
import com.huashitu.domain.Live;
import com.huashitu.mapper.LiveMapper;
import com.huashitu.service.live.ILiveService;
import com.huashitu.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by levy on 2016/11/22.
 */
@Service
public class LiveServiceImpl extends BaseServiceImpl<Live> implements ILiveService {

    @Autowired
    private LiveMapper liveMapper;

    @Override
    protected BaseMapper<Live> getBaseMapper() {
        return this.liveMapper;
    }

    @Override
    public List<Live> selectOnlineLive(int onlineStatus) {
        return liveMapper.selectOnlineLive(onlineStatus);
    }
}
