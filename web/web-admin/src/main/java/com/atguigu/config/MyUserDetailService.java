package com.atguigu.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Reference
    private AdminService adminService;
    @Reference
    private PermissionService permissionService;
    /**
     *
     * @param username 用户在登录页面输入的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据username获取Admin
        Admin admin = this.adminService.getByUsername(username);
        //如果admin为null，说明用户名错误，已经登录失败，直接抛出异常
        if(admin==null){
            throw  new UsernameNotFoundException("用户名错误");
        }
        //如果用户名称正确，也不需要你来比较密码，交给SpringSecurity来比较
        /*
        username:登录页面输入的用户名，同时也是数据库中的用户名，用户名正确
        password:数据库中存储的密文形式密码，不需要开发者进行比较，交给SpringSecurity来比较
        authorities：用户名已经正确，密码再正确后，就是登录成功；成功之后具有哪些权限呢？？
        目前用户名和密码都来自数据库，但是权限列表还是假的
         */
        //获取当前用户的权限code
        List<String> codeList = this.permissionService.findCodeListByAdminId(admin.getId());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String code :codeList){
            authorities.add(new SimpleGrantedAuthority(code));
        }
//        authorities.add(new SimpleGrantedAuthority("role_delete"));
//        authorities.add(new SimpleGrantedAuthority("admin_insert"));
        return new User(username,admin.getPassword(),authorities);
    }
}
