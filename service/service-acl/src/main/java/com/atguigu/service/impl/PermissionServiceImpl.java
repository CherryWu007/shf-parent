package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Permission;
import com.atguigu.entity.RolePermission;
import com.atguigu.helper.PermissionHelper;
import com.atguigu.mapper.PermissionMapper;
import com.atguigu.mapper.RolePermissionMapper;
import com.atguigu.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
 * @ClassName : PermissionServiceImpl.java
 * @createTime : 2022/8/31 19:03
 * @Email :851185679@qq.com
 * @Description :
 */
@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public BaseMapper<Permission> getEntityDao() {
        return permissionMapper;
    }

    @Override
    public List<Map<String, Object>> findPermissionsByRoleId(Long roleId) {
        //全部权限列表
        List<Permission> permissionList = permissionMapper.findAll();

        //获取角色已分配的权限数据
        List<Long> roleIdList = rolePermissionMapper.findPermissionIdListByRoleId(roleId);

        //根据上面的内容构建一个zTree数所对应的List,其中的元素不是Permission,而是Map,Map对应zTree的一个节点
        //参考文档：http://www.treejs.cn/v3/demo.php#_201
        // { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        List<Map<String,Object>> zNodes = new ArrayList<>();
        for(Permission permission : permissionList) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", permission.getId());
            map.put("pId", permission.getParentId());
            map.put("name", permission.getName());
            if(roleIdList.contains(permission.getId())) {
                map.put("checked", true);
            }
            //map.put("open",true);在后台打开，还可以在前台打开
            zNodes.add(map);
        };
        return zNodes;
    }

    @Override
    public void saveRolePermissionRealtionShip(Long roleId, Long[] permissionIds) {
        //删除该角色之前所有权限
        rolePermissionMapper.deleteByRoleId(roleId);
        //添加该角色现在分配的所有权限
        for (Long permissionId:permissionIds){

            RolePermission rolePermission=new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }
    }

    @Override
    public List<Permission> findMenuPermissionByAdminId(Long adminId) {
        List<Permission> permissionList = null;
        //admin账号id为：1
        if(adminId.longValue() == 1) {
            //如果是超级管理员，获取所有菜单
            permissionList = permissionMapper.findAll();
        } else {
            permissionList = permissionMapper.findListByAdminId(adminId);
        }
        //把权限数据构建成树形结构数据
        List<Permission> result = PermissionHelper.bulid(permissionList);
        return result;
    }

    @Override
    public List<String> findCodeListByAdminId(Long adminId) {

        if (adminId==1){
            return permissionMapper.findAllCode();
        }
        return permissionMapper.findCodeListByAdminId(adminId);
    }
}
