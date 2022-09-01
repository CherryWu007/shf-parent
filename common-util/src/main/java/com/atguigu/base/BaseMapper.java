package com.atguigu.base;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.base
 * @ClassName : BaseMapper.java
 * @createTime : 2022/8/21 22:36
 * @Email :851185679@qq.com
 * @Description :
 */

public interface BaseMapper<T> {



    Integer insert(T t);

    T getById(Serializable id);

    Integer update(T t);

    void delete(Serializable id);

    /**
     *
     * @param filters
     * @return Page是ArrayList子类，不仅包括角色数据，还包括分页数据
     */
    Page<T> findPage(Map<String, Object> filters);

}
