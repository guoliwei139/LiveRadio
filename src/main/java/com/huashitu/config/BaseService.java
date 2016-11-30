package com.huashitu.config;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public abstract interface BaseService<T>
{
    public abstract int delete(T paramT);

    public abstract int deleteByExample(Object paramObject);

    public abstract int deleteByPrimaryKey(Object paramObject);

    public abstract int insert(T paramT);

    public abstract int insertList(List<T> paramList);

    public abstract int insertSelective(T paramT);

    public abstract int insertUseGeneratedKeys(T paramT);

    public abstract List<T> select(T paramT);

    public abstract T selectOne(T paramT);

    public abstract List<T> selectAll();

    public abstract List<T> selectByExample(Object paramObject);

    public abstract List<T> selectByExampleAndRowBounds(Object paramObject, RowBounds paramRowBounds);

    public abstract T selectByPrimaryKey(Object paramObject);

    public abstract List<T> selectByRowBounds(T paramT, RowBounds paramRowBounds);

    public abstract int selectCount(T paramT);

    public abstract int selectCountByExample(Object paramObject);

    public abstract int updateByExampleSelective(@Param("record") T paramT, @Param("example") Object paramObject);

    public abstract int updateByExample(@Param("record") T paramT, @Param("example") Object paramObject);

    public abstract int updateByPrimaryKey(T paramT);

    public abstract int updateByPrimaryKeySelective(T paramT);

    public abstract List<T> pageList(Page<T> paramPage);
}
