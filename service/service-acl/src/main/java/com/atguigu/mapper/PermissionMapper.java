package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Permission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : PermissionMapper.java
 * @createTime : 2022/8/31 18:57
 * @Email :851185679@qq.com
 * @Description :
 */

public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> findAll();

    List<Permission> findListByAdminId(Long adminId);

    List<String> findAllCode();

    List<String> findCodeListByAdminId(Long adminId);
}
