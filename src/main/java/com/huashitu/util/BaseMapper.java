package com.huashitu.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * baseMapper
 * Created by Alex_ on 2016/9/25.
 */
public abstract interface BaseMapper<T> extends Mapper<T> , MySqlMapper<T> {
}
