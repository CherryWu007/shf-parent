package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Permission;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : IndexController.java
 * @createTime : 2022/8/21 0:28
 * @Email :851185679@qq.com
 * @Description :
 */
@Controller

public class IndexController {

    @Reference
    private PermissionService permissionService;

    @Reference
    private AdminService adminService;

    @RequestMapping
    public String toIndex(Model model){
        Long adminId=1L;//显示所有权限
        //Long adminId=10L;
        //获取当前用户的权限列表
        List<Permission> permissionList = permissionService.findMenuPermissionByAdminId(adminId);
        model.addAttribute("permissionList",permissionList);
        //获取当前用户的信息
        Admin admin = adminService.getById(adminId);
        model.addAttribute("admin",admin);
        return "frame/index";
    }
    @RequestMapping("/main")
    public String toMain(){
        return "frame/main";
    }

    @RequestMapping("/loginPage")
    public String login(){
        return "frame/login";
    }


}
