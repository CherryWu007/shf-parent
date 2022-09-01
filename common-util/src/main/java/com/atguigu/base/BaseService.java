package com.atguigu.base;

import com.github.pagehelper.Page;
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
 * @ClassName : BaseService.java
 * @createTime : 2022/8/21 22:45
 * @Email :851185679@qq.com
 * @Description :
 */

public interface BaseService<T> {

    Integer insert(T t);

    T getById(Serializable id);

    Integer update(T t);

    void delete(Serializable id);


    PageInfo<T> findPage(Map<String, Object> filters);
}
