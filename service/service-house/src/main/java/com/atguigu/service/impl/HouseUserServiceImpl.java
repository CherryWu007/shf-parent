package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseUser;
import com.atguigu.mapper.HouseBrokerMapper;
import com.atguigu.mapper.HouseUserMapper;
import com.atguigu.service.HouseUserService;
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
 * @ClassName : HouseBrokerServiceImpl.java
 * @createTime : 2022/8/28 11:42
 * @Email :851185679@qq.com
 * @Description :
 */
@Service(interfaceClass = HouseUserService.class)
@Transactional
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService{
    @Autowired
    private HouseUserMapper houseUserMapper;

    @Override
    public BaseMapper<HouseUser> getEntityDao() {
        return houseUserMapper;
    }

    @Override
    public List<HouseUser> findListByHouseId(Long houseId) {
        return houseUserMapper.findListByHouseId(houseId);
    }
}
