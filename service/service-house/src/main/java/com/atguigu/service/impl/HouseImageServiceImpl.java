package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseImage;
import com.atguigu.mapper.HouseBrokerMapper;
import com.atguigu.mapper.HouseImageMapper;
import com.atguigu.service.HouseBrokerService;
import com.atguigu.service.HouseImageService;
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
@Service(interfaceClass = HouseImageService.class)
@Transactional
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService{
    @Autowired
    private HouseImageMapper houseImageMapper;

    @Override
    public BaseMapper<HouseImage> getEntityDao() {
        return houseImageMapper;
    }

    @Override
    public List<HouseImage> findList(Long houseId, int type) {
        return houseImageMapper.findList(houseId,type);
    }
}
