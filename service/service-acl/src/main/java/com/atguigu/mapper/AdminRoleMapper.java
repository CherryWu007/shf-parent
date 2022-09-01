package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.AdminRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : AdminRoleMapper.java
 * @createTime : 2022/8/31 16:52
 * @Email :851185679@qq.com
 * @Description :
 */

public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    List<Long> findRoleIdsByAdminId();

    void deleteByAdminId(Long adminId);
}
