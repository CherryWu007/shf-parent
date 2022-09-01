package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : HouseUserController.java
 * @createTime : 2022/8/28 11:57
 * @Email :851185679@qq.com
 * @Description :
 */
@Controller
@RequestMapping("/houseUser")
public class HouseUserController extends BaseController {
    @Reference
    private HouseUserService houseUserService;

    /**
     * 新增房东
     * @param houseId
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String create(Long houseId, Model model){
        model.addAttribute("houseId",houseId);
        return "houseUser/create";
    }

    @RequestMapping("/save")
    public String save(HouseUser houseUser){
        houseUserService.insert(houseUser);
        return "common/successPage";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        HouseUser houseUser = houseUserService.getById(id);
        model.addAttribute("houseUser",houseUser);
        return "houseUser/edit";
    }

    /**
     * 保存修改
     * @param houseUser
     * @return
     */
    @RequestMapping("/update")
    public String update(HouseUser houseUser){
        houseUserService.update(houseUser);
        return "common/successPage";
    }
    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable Long houseId, @PathVariable Long id){
        houseUserService.delete(id);
        return "redirect:/house/"+houseId;
    }
}
