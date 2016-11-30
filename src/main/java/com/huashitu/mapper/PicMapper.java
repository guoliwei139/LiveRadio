package com.huashitu.mapper;


import com.huashitu.domain.Pic;
import com.huashitu.util.BaseMapper;

public interface PicMapper extends BaseMapper<Pic> {

    public Long insertPic(Pic p);
}