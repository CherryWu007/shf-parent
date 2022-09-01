package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.House;
import com.atguigu.entity.bo.HouseQueryBo;
import com.atguigu.entity.vo.HouseVo;
import com.github.pagehelper.PageInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service
 * @ClassName : HouseService.java
 * @createTime : 2022/8/26 16:36
 * @Email :851185679@qq.com
 * @Description :
 */

public interface HouseService extends BaseService<House> {
    Integer publish(House house);

    PageInfo<HouseVo> findPage(Integer pageNum, Integer pageSize, HouseQueryBo houseQueryBo);
}
