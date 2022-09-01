package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service.impl
 * @ClassName : RoleService.java
 * @createTime : 2022/8/20 23:06
 * @Email :851185679@qq.com
 * @Description :
 */

public interface RoleService extends BaseService<Role> {

    public List<Role> findAll();

    /**
     * 根据用户获取角色数据
     * @param adminId
     * @return
     */
    Map<String, Object> findRoleByAdminId(Long adminId);

    /**
     * 分配角色后，原来有的被删除
     * 分配角色后，原来有的被保留、
     * 分配角色后，新增了新角色
     * 思路：删除之前所有已经分配的，添加现在所有的新分配的
     * @param adminId
     * @param roleIds
     */
    void saveUserRoleRealtionShip(Long adminId, Long[] roleIds);


}
