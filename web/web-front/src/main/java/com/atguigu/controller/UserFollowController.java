package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;
import com.atguigu.entity.vo.UserFollowVo;
import com.atguigu.result.Result;
import com.atguigu.service.UserFollowService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : UserFollowController.java
 * @createTime : 2022/8/30 22:26
 * @Email :851185679@qq.com
 * @Description :完成关注操作
 */
@RestController
@RequestMapping("/userFollow")
public class UserFollowController {

    @Reference
    private UserFollowService userFollowService;


    @GetMapping("/auth/follow/{houseId}")
    public Result follow(@PathVariable Long houseId, HttpServletRequest request){
        //获取当前用户id
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        UserFollow userFollow=new UserFollow();
        userFollow.setUserId(userId);
        userFollow.setHouseId(houseId);
        //添加关注
        userFollowService.follow(userFollow);
        return Result.ok();
    }

    @RequestMapping("/auth/list/{pageNum}/{pageSize}")
    public Result<PageInfo<UserFollowVo>> list(@PathVariable Integer pageNum, @PathVariable Integer pageSize, HttpServletRequest request){
        //获取当前用户的id
        UserInfo userInfo =(UserInfo) request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        PageInfo<UserFollowVo> pageInfo = this.userFollowService.findListPage(pageNum,pageSize,userInfo.getId());
        return Result.ok(pageInfo);
    }

    @RequestMapping("/auth/cancelFollow/{id}")
    public Result cancelFollow(@PathVariable Long id){
        userFollowService.delete(id);

        return Result.ok();
    }
}
