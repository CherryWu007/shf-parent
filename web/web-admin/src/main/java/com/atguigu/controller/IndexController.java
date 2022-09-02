package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Permission;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        //Long adminId=1L;//显示所有权限
        //从SpringSecurity的上下文中获取当前用户的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =(User) authentication.getPrincipal();
        String username = user.getUsername();
        Admin admin = adminService.getByUsername(username);

        //Long adminId=10L;
        //获取当前用户的权限列表
        List<Permission> permissionList = permissionService.findMenuPermissionByAdminId(admin.getId());
        model.addAttribute("permissionList",permissionList);
        //获取当前用户的信息
        //Admin admin = adminService.getById(adminId);
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

    /**
     * 获取当前登录信息
     * @return
     */
    @GetMapping("/getInfo")
    @ResponseBody
    public Object getInfo() {
        //从SpringSecurity的上下文中获取当前用户的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //返回认证信息的Principal(当前用户信息)
        return authentication.getPrincipal();
    }

    @GetMapping("/auth")
    public String auth(){
        return "auth";
    }

}
