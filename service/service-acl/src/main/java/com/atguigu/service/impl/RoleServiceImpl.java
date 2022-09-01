package com.atguigu.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.AdminRole;
import com.atguigu.entity.Role;
import com.atguigu.mapper.AdminRoleMapper;
import com.atguigu.mapper.RoleMapper;
import com.atguigu.service.AdminService;
import com.atguigu.service.RoleService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service.impl
 * @ClassName : RoleServiceImpl.java
 * @createTime : 2022/8/20 23:07
 * @Email :851185679@qq.com
 * @Description :
 */
@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public List<Role> findAll() {
        return this.roleMapper.findAll();
    }



    @Override
    public Map<String, Object> findRoleByAdminId(Long adminId) {
        //获取所有角色
        List<Role> roleList = this.roleMapper.findAll();
        //获取当前用户已经分配的角色id列表
        List<Long> roleIdList=adminRoleMapper.findRoleIdsByAdminId();
        //将所有的角色分成两部分：当前已经分配的、当前没有分配的
        List<Role> noAssignRoleList = new ArrayList<>();//没有分配

        List<Role> assignRoleList = new ArrayList<>();//已经分配的
        for (Role role:roleList) {
            if (roleIdList.contains(role.getId())){
                assignRoleList.add(role);
            }
            noAssignRoleList.add(role);
        }
        //将两部分角色封装到map中并返回
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("noAssignRoleList", noAssignRoleList);
        roleMap.put("assignRoleList", assignRoleList);
        return roleMap;

    }

    /**
     * 分配角色后，原来有的被删除
     * 分配角色后，原来有的被保留、
     * 分配角色后，新增了新角色
     * 思路：删除之前所有已经分配的，添加现在所有的新分配的
     * @param adminId
     * @param roleIds
     */
    @Override
    public void saveUserRoleRealtionShip(Long adminId, Long[] roleIds) {
        //删除之前已经分配的
        adminRoleMapper.deleteByAdminId(adminId);
        //添加现在所有新分配的
        for (Long roleId:roleIds){
            //如果roleId为null，直接执行下次循环，循环体后面的语句就不执行了
            //“1，3，5”前端页面传来的roleIds的字符串
            //到了SpringMVC会自动转换为数组[1,3,5,null]
            if(StringUtils.isEmpty(roleId)) {
                continue;
            }
            AdminRole adminRole = new AdminRole();
            adminRole.setRoleId(roleId);
            adminRole.setAdminId(adminId);
            adminRoleMapper.insert(adminRole);

        }
    }


    @Override
    public BaseMapper<Role> getEntityDao() {
        return roleMapper;
    }
}
