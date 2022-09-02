package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service
 * @ClassName : PermissionService.java
 * @createTime : 2022/8/31 19:00
 * @Email :851185679@qq.com
 * @Description :
 */

public interface PermissionService extends BaseService<Permission> {


    /**
     * 根据角色获取授权权限数据
     * @return
     * 为什么要赶回map
     * 因为返回值是zTree树的json数据，map中的key就是zTree的属性
     * 然后背后的FastJSON负责将map转换为JSON字符串
     *
     */
    List<Map<String,Object>> findPermissionsByRoleId(Long roleId);

    void saveRolePermissionRealtionShip(Long roleId, Long[] permissionIds);

    /**
     * 获取用户菜单权限
     * @param adminId
     * @return
     */
    List<Permission> findMenuPermissionByAdminId(Long adminId);

    List<String> findCodeListByAdminId(Long adminId);
}
