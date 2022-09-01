package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.RolePermission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : RolePermissionMapper.java
 * @createTime : 2022/8/31 19:00
 * @Email :851185679@qq.com
 * @Description :
 */

public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    List<Long> findPermissionIdListByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}
