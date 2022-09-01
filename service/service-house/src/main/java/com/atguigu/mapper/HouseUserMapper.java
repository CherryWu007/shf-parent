package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseUser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : HouseBrokerMapper.java
 * @createTime : 2022/8/28 11:36
 * @Email :851185679@qq.com
 * @Description :
 */

public interface HouseUserMapper extends BaseMapper<HouseUser> {
    List<HouseUser> findListByHouseId(Long houseId);
}
