package com.huashitu.config;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huashitu.util.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected abstract BaseMapper<T> getBaseMapper();

    public int delete(T t) {
        return getBaseMapper().delete(t);
    }

    public int deleteByExample(Object o) {
        return getBaseMapper().deleteByExample(o);
    }

    public int deleteByPrimaryKey(Object o) {
        return getBaseMapper().deleteByPrimaryKey(o);
    }

    public int insert(T t) {
        return getBaseMapper().insert(t);
    }

    public int insertList(List<T> list) {
        return getBaseMapper().insertList(list);
    }

    public int insertSelective(T t) {
        return getBaseMapper().insertSelective(t);
    }

    public int insertUseGeneratedKeys(T t) {
        return getBaseMapper().insertUseGeneratedKeys(t);
    }

    public List<T> select(T t) {
        return getBaseMapper().select(t);
    }

    public T selectOne(T t) {
        return getBaseMapper().selectOne(t);
    }

    public List<T> selectAll() {
        return getBaseMapper().selectAll();
    }

    public List<T> selectByExample(Object o) {
        return getBaseMapper().selectByExample(o);
    }

    public List<T> selectByExampleAndRowBounds(Object o, RowBounds r) {
        return getBaseMapper().selectByExampleAndRowBounds(o, r);
    }

    public T selectByPrimaryKey(Object o) {
        return getBaseMapper().selectByPrimaryKey(o);
    }

    public List<T> selectByRowBounds(T t, RowBounds r) {
        return getBaseMapper().selectByRowBounds(t, r);
    }

    public int selectCount(T t) {
        return getBaseMapper().selectCount(t);
    }

    public int selectCountByExample(Object o) {
        return getBaseMapper().selectCountByExample(o);
    }

    public int updateByExampleSelective(@Param("record") T t, @Param("example") Object o) {
        return getBaseMapper().updateByExampleSelective(t, o);
    }

    public int updateByExample(@Param("record") T t, @Param("example") Object o) {
        return getBaseMapper().updateByExample(t, o);
    }

    public int updateByPrimaryKey(T t) {
        return getBaseMapper().updateByPrimaryKey(t);
    }

    public int updateByPrimaryKeySelective(T t) {
        return getBaseMapper().updateByPrimaryKeySelective(t);
    }

    public List<T> pageList(Page<T> page) {
        if ((page.getPageNum() != 0) && (page.getPageSize() != 0)) {
            PageHelper.startPage(page.getPageNum(), page.getPageSize(), "id");
        }
        return getBaseMapper().selectAll();
    }
}