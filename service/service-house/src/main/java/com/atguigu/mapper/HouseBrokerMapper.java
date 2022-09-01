package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.HouseBroker;
import org.apache.ibatis.annotations.Param;

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

public interface HouseBrokerMapper extends BaseMapper<HouseBroker> {
    List<HouseBroker> findListByHouseId(@Param("houseId") Long houseId);
}
