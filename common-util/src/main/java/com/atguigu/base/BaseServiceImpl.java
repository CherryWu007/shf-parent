package com.atguigu.base;

import com.atguigu.util.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.base
 * @ClassName : BaseServiceImpl.java
 * @createTime : 2022/8/21 22:51
 * @Email :851185679@qq.com
 * @Description :
 */

public abstract class BaseServiceImpl<T> implements BaseService<T>{

    public abstract BaseMapper<T> getEntityDao();

    @Override
    public Integer insert(T t) {
        return getEntityDao().insert(t);

    }

    @Override
    public T getById(Serializable id) {
        return getEntityDao().getById(id);
    }

    @Override
    public Integer update(T t) {
        return getEntityDao().update(t);
    }

    @Override
    public void delete(Serializable id) {
        getEntityDao().delete(id);
    }

    @Override
    public PageInfo<T> findPage(Map<String, Object> filters) {
        //开启分页插件

        int pageNum= CastUtil.castInt(filters.get("pageNum"));
        int pageSize=CastUtil.castInt(filters.get("pageSize"));
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper获取分页的role
        //参数是filters，只会用到条件roleName
        Page<T> page = getEntityDao().findPage(filters);
        //转换pageInfo
        return new PageInfo<>(page);
    }
}
