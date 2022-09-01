package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Role;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : RoleMapper.java
 * @createTime : 2022/8/20 22:22
 * @Email :851185679@qq.com
 * @Description :可以继承父接口方法，还可以写自己的方法
 */

public interface RoleMapper extends BaseMapper<Role> {

    public List<Role> findAll();
}
