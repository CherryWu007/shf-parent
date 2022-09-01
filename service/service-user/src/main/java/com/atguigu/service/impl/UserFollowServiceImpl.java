package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.UserFollow;
import com.atguigu.entity.vo.UserFollowVo;
import com.atguigu.mapper.UserFollowMapper;
import com.atguigu.service.DictService;
import com.atguigu.service.UserFollowService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service.impl
 * @ClassName : UserFollowServiceImpl.java
 * @createTime : 2022/8/30 22:24
 * @Email :851185679@qq.com
 * @Description :
 */
@Service(interfaceClass = UserFollowService.class)
@Transactional
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {
    @Autowired
    private UserFollowMapper userFollowMapper;
    @Reference
    private DictService dictService;

    @Override
    public BaseMapper<UserFollow> getEntityDao() {
        return userFollowMapper;
    }

    @Override
    public void follow(UserFollow userFollow) {
        userFollowMapper.insert(userFollow);
    }

    @Override
    public boolean isFollow(Long houseId, Long userId) {
        Integer count=this.userFollowMapper.isFollow(houseId,userId);
        if (count==0){
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<UserFollowVo> findListPage(Integer pageNum, Integer pageSize, Long userId) {
        //分页
        PageHelper.startPage(pageNum,pageSize);
        Page<UserFollowVo> page= this.userFollowMapper.findListPage(userId);
        //根据dictId获取dictName：houseTypeName floorName  directionName
        //处理数据
        List<UserFollowVo> userFollowVoList = page.getResult();
        for(UserFollowVo userFollowVo:userFollowVoList){
            //        房屋户型：houseTypeId--->houseTypeName
            String houseTypeName = this.dictService.getNameById(userFollowVo.getHouseTypeId());
            userFollowVo.setHouseTypeName(houseTypeName);
            //        所在楼层：floorId---->floorName
            String floorName = this.dictService.getNameById(userFollowVo.getFloorId());
            userFollowVo.setFloorName(floorName);
            //        房屋朝向：
            String directionName = this.dictService.getNameById(userFollowVo.getDirectionId());
            userFollowVo.setDirectionName(directionName);

        }

        return new PageInfo(page);
    }
}
