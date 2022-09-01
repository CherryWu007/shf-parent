package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.House;
import com.atguigu.entity.bo.HouseQueryBo;
import com.atguigu.entity.vo.HouseVo;
import com.atguigu.mapper.DictMapper;
import com.atguigu.mapper.HouseMapper;
import com.atguigu.service.HouseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service.impl
 * @ClassName : HouseServiceImpl.java
 * @createTime : 2022/8/26 16:36
 * @Email :851185679@qq.com
 * @Description :
 */
@Service(interfaceClass = HouseService.class)
@Transactional
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public BaseMapper<House> getEntityDao() {
        return houseMapper;
    }

    @Override
    public House getById(Serializable id) {
        House house= super.getById(id);
        //房屋户型：houseTypeId--->houseTypeName
        String houseTypeName = dictMapper.getNameById(house.getHouseTypeId());
        house.setHouseTypeName(houseTypeName);
        //所在楼层：floorId---->floorName
        String floorName = dictMapper.getNameById(house.getFloorId());
        house.setFloorName(floorName);
        //建筑结构：
        String buildStructureName = dictMapper.getNameById(house.getBuildStructureId());
        house.setBuildStructureName(buildStructureName);
        //房屋朝向：
        String directionName = dictMapper.getNameById(house.getDirectionId());
        house.setDirectionName(directionName);
        //装修情况：
        String decorationName = dictMapper.getNameById(house.getDecorationId());
        house.setDecorationName(decorationName);
        //房屋用途：
        String houseUseName = dictMapper.getNameById(house.getHouseUseId());
        house.setHouseUseName(houseUseName);
        return house;
    }

    @Override
    public Integer publish(House house) {
        return houseMapper.update(house);
    }

    @Override
    public PageInfo<HouseVo> findPage(Integer pageNum, Integer pageSize, HouseQueryBo houseQueryBo) {
        //开启分页插件，
        PageHelper.startPage(pageNum,pageSize);
        //查询符合条件的当前页数据
        Page<HouseVo> page= houseMapper.findListPage(houseQueryBo);
        //处理数据
        List<HouseVo> houseVoList = page.getResult();
        for (HouseVo houseVo:houseVoList){
            //房屋户型：houseTypeId--->houseTypeName
            String houseTypeName = dictMapper.getNameById(houseVo.getHouseTypeId());
            houseVo.setHouseTypeName(houseTypeName);
            //所在楼层：floorId---->floorName
            String floorName = dictMapper.getNameById(houseVo.getFloorId());
            houseVo.setFloorName(floorName);

            //房屋朝向：
            String directionName = dictMapper.getNameById(houseVo.getDirectionId());
            houseVo.setDirectionName(directionName);

        }
        //返回数据
        return new  PageInfo<>(page);
    }
}
