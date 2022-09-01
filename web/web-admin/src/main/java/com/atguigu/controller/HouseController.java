package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.*;
import com.atguigu.service.*;
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
 * @ClassName : HouseController.java
 * @createTime : 2022/8/26 16:42
 * @Email :851185679@qq.com
 * @Description :
 */
@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {

    @Reference
    private HouseService houseService;
    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;
    @Reference
    private HouseImageService houseImageService;
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HouseUserService houseUserService;

    /**
     * 查询
     * @param request
     * @param model
     * @return
     */
    @RequestMapping
    public String findPage(HttpServletRequest request, Model model){
        //接收查询条件和分页条件
        Map<String, Object> filters = this.getFilters(request);
        model.addAttribute("filters",filters);
        //查询指定页的房源信息含分页信息
        PageInfo<House> page = this.houseService.findPage(filters);
        model.addAttribute("page",page);
        //查询所有小区信息
        List<Community> communityList= this.communityService.findAll();
        model.addAttribute("communityList",communityList);

        //查询六个字典项列表并放入model
        List<Dict> houseTypeList = this.dictService.findListByDictCode("houseType");
        model.addAttribute("houseTypeList",houseTypeList);
        List<Dict> floorList = this.dictService.findListByDictCode("floor");
        model.addAttribute("floorList",floorList);
        List<Dict> buildStructureList = this.dictService.findListByDictCode("buildStructure");
        model.addAttribute("buildStructureList",buildStructureList);
        List<Dict> directionList = this.dictService.findListByDictCode("direction");
        model.addAttribute("directionList",directionList);
        List<Dict> decorationList = this.dictService.findListByDictCode("decoration");
        model.addAttribute("decorationList",decorationList);
        List<Dict> houseUseList = this.dictService.findListByDictCode("houseUse");
        model.addAttribute("houseUseList",houseUseList);

        return "house/index";
    }

    /**
     * 新增页面
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String create(Model model){

        //查询所有小区信息
        List<Community> communityList= this.communityService.findAll();
        model.addAttribute("communityList",communityList);

        //查询六个字典项列表并放入model
        List<Dict> houseTypeList = this.dictService.findListByDictCode("houseType");
        model.addAttribute("houseTypeList",houseTypeList);
        List<Dict> floorList = this.dictService.findListByDictCode("floor");
        model.addAttribute("floorList",floorList);
        List<Dict> buildStructureList = this.dictService.findListByDictCode("buildStructure");
        model.addAttribute("buildStructureList",buildStructureList);
        List<Dict> directionList = this.dictService.findListByDictCode("direction");
        model.addAttribute("directionList",directionList);
        List<Dict> decorationList = this.dictService.findListByDictCode("decoration");
        model.addAttribute("decorationList",decorationList);
        List<Dict> houseUseList = this.dictService.findListByDictCode("houseUse");
        model.addAttribute("houseUseList",houseUseList);

        return "house/create";
    }

    /**
     * 保存信息
     * @param house
     * @return
     */
    @RequestMapping("/save")
    public String save(House house){
        this.houseService.insert(house);
        return "common/successPage";
    }

    /**
     * 修改房源信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        List<Community> communityList= this.communityService.findAll();
        model.addAttribute("communityList",communityList);

        //查询六个字典项列表并放入model
        List<Dict> houseTypeList = this.dictService.findListByDictCode("houseType");
        model.addAttribute("houseTypeList",houseTypeList);
        List<Dict> floorList = this.dictService.findListByDictCode("floor");
        model.addAttribute("floorList",floorList);
        List<Dict> buildStructureList = this.dictService.findListByDictCode("buildStructure");
        model.addAttribute("buildStructureList",buildStructureList);
        List<Dict> directionList = this.dictService.findListByDictCode("direction");
        model.addAttribute("directionList",directionList);
        List<Dict> decorationList = this.dictService.findListByDictCode("decoration");
        model.addAttribute("decorationList",decorationList);
        List<Dict> houseUseList = this.dictService.findListByDictCode("houseUse");
        model.addAttribute("houseUseList",houseUseList);

        //查询指定id 房源信息
        House house = this.houseService.getById(id);
        model.addAttribute("house",house);

        return "house/edit";
    }

    /**
     * 完成修改的保存操作
     * @param house
     * @return
     */
    @RequestMapping("/update")
    public String update(House house){
        this.houseService.update(house);
        return "common/successPage";
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        houseService.delete(id);
        return "redirect:/house";
    }

    @RequestMapping("/publish/{id}/{status}")
    public String publish(@PathVariable Long id, @PathVariable Integer status){
        House house = new House();
        house.setId(id);
        house.setStatus(status);
        houseService.publish(house);

        return "redirect:/house";
    }

    /**
     * 查询信息并跳转至详情页面
     * @param id
     * @return
     */
    @RequestMapping("/{houseId}")
    public String detail(@PathVariable("houseId") Long id,Model model){
        //查询指定id的房源信息
        House house = houseService.getById(id);
        model.addAttribute("house",house);
        //小区信息
        Community community = communityService.getById(house.getCommunityId());
        model.addAttribute("community",community);
        //查询房源图片和房东图片
        List<HouseImage> houseImage1List= houseImageService.findList(id,1);
        model.addAttribute("houseImage1List",houseImage1List);

        List<HouseImage> houseImage2List= houseImageService.findList(id,2);
        model.addAttribute("houseImage2List",houseImage2List);
        //该房源的经纪人列表
        List<HouseBroker> houseBrokerList= houseBrokerService.findListByHouseId(id);
        model.addAttribute("houseBrokerList",houseBrokerList);
        //房东信息
        List<HouseUser> houseUserList=houseUserService.findListByHouseId(id);
        model.addAttribute("houseUserList",houseUserList);
        return "house/show";
    }





}
