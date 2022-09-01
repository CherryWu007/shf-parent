package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Role;
import com.atguigu.service.PermissionService;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : RoleController.java
 * @createTime : 2022/8/20 23:07
 * @Email :851185679@qq.com
 * @Description :
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Reference
    private RoleService roleService;


    @RequestMapping
    public String toIndex(Model model,HttpServletRequest request){
        //获取查询条件和分页数据
        Map<String, Object> filters = this.getFilters(request);
        //调用业务层获取分页数据(pageInfo:包括roleList、pageNum、pageSize)
        PageInfo<Role> page = roleService.findPage(filters);
        //页面跳转
        model.addAttribute("page",page);//返回分页数据roleList、pageNum、pageSize、total、pages...
        model.addAttribute("filters",filters);//返回查询条件和分页信息pageNum、pageSize
        return "role/index";
    }

    /*@RequestMapping
    public String toIndex(Model model){
        List<Role> all = this.roleService.findAll();
        model.addAttribute("all",all);
        return "role/index";
    }*/
    @RequestMapping("/create")
    public String create(){
        return "role/create";
    }
    @RequestMapping("/save")
    public String save(Role role){
        this.roleService.insert(role);
        return "common/successPage";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        Role role=this.roleService.getById(id);
        model.addAttribute("role",role);
        return "role/edit";
    }

    @RequestMapping("/update")
    public String update(Role role){
        this.roleService.update(role);
        return "common/successPage";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        this.roleService.delete(id);
        //跳转至当前findAll方法查询最新数据
        return "redirect:/role";
    }

    @Reference
    private PermissionService permissionService;

    @RequestMapping("/assignShow/{roleId}")
    public String assignShow(@PathVariable Long roleId,Model model){
        List<Map<String, Object>> zNodes = permissionService.findPermissionsByRoleId(roleId);
        model.addAttribute("zNodes",zNodes);
        model.addAttribute("roleId",roleId);

        return "role/assignShow";
    }

    /**
     * 给角色分配权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    @RequestMapping("/assignPermission")
    public String assignPermission(Long roleId,Long[]permissionIds){
        permissionService.saveRolePermissionRealtionShip(roleId,permissionIds);
        return "common/successPage";
    }

}
