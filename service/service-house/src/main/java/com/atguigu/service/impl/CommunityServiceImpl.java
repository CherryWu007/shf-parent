package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Community;
import com.atguigu.mapper.CommunityMapper;
import com.atguigu.mapper.DictMapper;
import com.atguigu.service.CommunityService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service.impl
 * @ClassName : CommunityServiceImpl.java
 * @createTime : 2022/8/25 0:35
 * @Email :851185679@qq.com
 * @Description :
 */
@Service(interfaceClass = CommunityService.class)
@Transactional
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public BaseMapper<Community> getEntityDao() {
        return communityMapper;
    }

    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        //开启分页插件

        int pageNum= CastUtil.castInt(filters.get("pageNum"));
        int pageSize=CastUtil.castInt(filters.get("pageSize"));
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper获取分页的role
        //参数是filters，只会用到条件roleName
        Page<Community> page = getEntityDao().findPage(filters);
        //给每一个Community指定areaName和plateName
        List<Community> communityList = page.getResult();
        for (Community community:communityList){

            String areaName = this.dictMapper.getNameById(community.getAreaId());

            String plateName = this.dictMapper.getNameById(community.getPlateId());
            //areaId-->areaName
            community.setAreaName(areaName);
            //plateId-->plateName
            community.setPlateName(plateName);
        }
        //转换pageInfo
        return new PageInfo<>(page);
    }

    @Override
    public List<Community> findAll() {
        return this.communityMapper.findAll();
    }

    @Override
    public Community getById(Serializable id) {
        Community community= super.getById(id);
        String areaName = this.dictMapper.getNameById(community.getAreaId());

        String plateName = this.dictMapper.getNameById(community.getPlateId());
        //areaId-->areaName
        community.setAreaName(areaName);
        //plateId-->plateName
        community.setPlateName(plateName);
        return community;
    }
}
