package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.atguigu.service.RoleService;
import com.atguigu.util.QiniuUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : AdminController.java
 * @createTime : 2022/8/22 0:56
 * @Email :851185679@qq.com
 * @Description :
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Reference
    private AdminService adminService;
    @Reference
    private RoleService roleService;

    @RequestMapping
    public String findPage(HttpServletRequest request, Model model){

        //获取查询条件和分页参数
        Map<String, Object> filters = this.getFilters(request);
        //调用业务层获取分页数据
        PageInfo<Admin> page = this.adminService.findPage(filters);
        model.addAttribute("page",page);
        model.addAttribute("filters",filters);
        //跳转至指定页面
        return "admin/index";
    }

    @RequestMapping("/create")
    public String create(){
        return "admin/create";
    }
    @RequestMapping("/save")
    public String save(Admin admin){
        admin.setHeadUrl("http://47.93.148.192:8080/group1/M00/03/F0/rBHu8mHqbpSAU0jVAAAgiJmKg0o148.jpg");
        this.adminService.insert(admin);
        return "common/successPage";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model ){
        Admin admin = this.adminService.getById(id);
        model.addAttribute("admin",admin);
        return "admin/edit";
    }
    @RequestMapping("/update")
    public String update(Admin admin){
        this.adminService.update(admin);
        return "common/successPage";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        this.adminService.delete(id);
        return "redirect:/admin";
    }

    /**
     * 跳转至上传头像页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/uploadShow/{id}")
    public String uploadShow(@PathVariable Long id,Model model){
        model.addAttribute("id",id);
        return "admin/upload";

    }

    @RequestMapping("/upload/{id}")
    public String upload(@PathVariable Long id, Model model, MultipartFile file) throws IOException {
        //上传图片至七牛云(避免同名文件覆盖，需要重新命名UUID)
        String originalFilename = file.getOriginalFilename();//指定图片
        String fileName = UUID.randomUUID().toString();//没有扩展名
        assert originalFilename != null;
        fileName +=originalFilename.substring(originalFilename.lastIndexOf("."));

        QiniuUtils.upload2Qiniu(file.getBytes(),fileName);
        //修改数据库中用户头像地址
        Admin admin=new Admin();
        admin.setId(id);
        admin.setHeadUrl("http://rhbq9flmw.hb-bkt.clouddn.com/"+fileName);
        adminService.update(admin);
        //
        return "common/successPage";
    }

    /**
     * 进入分配角色页面
     * @param adminId
     * @return
     */
    @GetMapping("/assignShow/{adminId}")
    public String assignShow(ModelMap model, @PathVariable Long adminId) {
        Map<String, Object> roleMap = roleService.findRoleByAdminId(adminId);
        //
        model.addAllAttributes(roleMap);
        model.addAttribute("adminId", adminId);

        return "admin/assignShow";
    }

    @RequestMapping("/assignRole")
    public String assignRole(Long adminId,Long[]roleIds){
        roleService.saveUserRoleRealtionShip(adminId,roleIds);
        return "common/successPage";

    }

}
