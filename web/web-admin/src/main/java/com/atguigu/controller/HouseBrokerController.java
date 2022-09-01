package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.AdminService;
import com.atguigu.service.HouseBrokerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : HouseBrokerController.java
 * @createTime : 2022/8/28 11:56
 * @Email :851185679@qq.com
 * @Description :
 */
@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController extends BaseController {
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private AdminService adminService;

    @RequestMapping("/create")
    public String create(Long houseId, Model model){
        model.addAttribute("houseId",houseId);
        //获取admin列表
        List<Admin> adminList=adminService.findAll();
        model.addAttribute("adminList",adminList);
        return "houseBroker/create";
    }
    @RequestMapping("/save")
    public String save(HouseBroker houseBroker){
        //根据经纪人id获得admin信息
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        houseBrokerService.insert(houseBroker);
        return "common/successPage";
    }

    /**
     * 修改经纪人，替换经纪人
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        //当前经纪人
        HouseBroker houseBroker=houseBrokerService.getById(id);
        model.addAttribute("houseBroker",houseBroker);
        //用户列表
        List<Admin> adminList=adminService.findAll();
        model.addAttribute("adminList",adminList);
        return "houseBroker/edit";
    }
@RequestMapping("/update")
    public String update(HouseBroker houseBroker){
        //根据经纪人id获得admin信息

        Admin admin = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        houseBrokerService.update(houseBroker);
        return "common/successPage";
    }
    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable Long houseId, @PathVariable Long id){//brokerId,不是adminId
        houseBrokerService.delete(id);
        return "redirect:/house/"+houseId;
    }
}
