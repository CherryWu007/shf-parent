package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.*;
import com.atguigu.entity.bo.HouseQueryBo;
import com.atguigu.entity.vo.HouseVo;
import com.atguigu.result.Result;
import com.atguigu.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : Housecontroller.java
 * @createTime : 2022/8/29 20:45
 * @Email :851185679@qq.com
 * @Description :
 */
@RestController
@RequestMapping("/house")
public class HouseController {
    @Reference
    private HouseService houseService;
    @Reference
    private CommunityService communityService;
    @Reference
    private HouseImageService houseImageService;
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private UserFollowService userFollowService;


    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable Integer pageNum,
                           @PathVariable Integer pageSize,
                           @RequestBody HouseQueryBo houseQueryBo){


        PageInfo<HouseVo> page = houseService.findPage(pageNum, pageSize, houseQueryBo);
        return Result.ok(page);
    }


    @RequestMapping("/info/{id}")
    public Result info(@PathVariable Long id, Model model, HttpServletRequest request){
        Map map=new HashMap();

        //获取指定id的房源
        House house = this.houseService.getById(id);
        map.put("house",house);
        //获取当前房源的小区信息
        Community community = communityService.getById(house.getCommunityId());
        map.put("community",community);
        //获取经纪人列表
        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(id);
        map.put("houseBrokerList",houseBrokerList);
        //获取当前房源的图片信息（type=1）
        List<HouseImage> houseImage1List = houseImageService.findList(id, 1);
        map.put("houseImage1List",houseImage1List);
        //获取房源是否已经被当前用户关注的信息（还没有实现登录功能，目前设置为false）

        boolean isFollow =false;//用户没有登录，处于没有关注
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("USER");

        if(userInfo!=null){//判断用户是否已经登录
            isFollow = userFollowService.isFollow(id,userInfo.getId());
        }

        map.put("isFollow",isFollow);
        return Result.ok(map);
    }
}
