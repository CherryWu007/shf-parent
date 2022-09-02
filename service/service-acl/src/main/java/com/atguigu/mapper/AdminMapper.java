package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Admin;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : AdminMapper.java
 * @createTime : 2022/8/22 0:52
 * @Email :851185679@qq.com
 * @Description :
 */

public interface AdminMapper extends BaseMapper<Admin> {
    List<Admin> findAll();

    Admin getByUsername(String username);
}
