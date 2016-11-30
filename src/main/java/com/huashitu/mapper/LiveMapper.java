package com.huashitu.mapper;

import com.huashitu.domain.Live;
import com.huashitu.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LiveMapper extends BaseMapper<Live> {
    public List<Live> selectOnlineLive(@Param("onlineStatus") int onlineStatus);
}