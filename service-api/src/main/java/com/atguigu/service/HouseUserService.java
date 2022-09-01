package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseUser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service
 * @ClassName : HouseBrokerService.java
 * @createTime : 2022/8/28 11:39
 * @Email :851185679@qq.com
 * @Description :
 */

public interface HouseUserService extends BaseService<HouseUser> {
    List<HouseUser> findListByHouseId(Long houseId);
}
