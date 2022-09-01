package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : CommunityController.java
 * @createTime : 2022/8/25 0:38
 * @Email :851185679@qq.com
 * @Description :
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;

    @RequestMapping
    public String findPage(HttpServletRequest request, Model model){
        //获取查询条件和分页数据并保存到model
        Map<String, Object> filters = super.getFilters(request);
        model.addAttribute("filters",filters);
        //查询出所有区域并保存到model
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("areaList",areaList);
        //获取分页数据并保存
        PageInfo<Community> page = this.communityService.findPage(filters);
        model.addAttribute("page",page);
        return "community/index";
    }

    /**
     * 跳转至添加窗口
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String create(Model model){

        //查询出所有区域并保存到model
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("areaList",areaList);

        return "community/create";
    }

    @RequestMapping("/save")
    public String save(Community community){
        this.communityService.insert(community);
        return "common/successPage";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        //查询指定id的小区信息
        Community community = this.communityService.getById(id);
        model.addAttribute("community",community);
        //查询北京所有的区域
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("areaList",areaList);
        return "community/edit";
    }

    @RequestMapping("/update")
    public String update(Community community){
        this.communityService.update(community);
        return "common/successPage";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        this.communityService.delete(id);
        return "redirect:/community";
    }

}
