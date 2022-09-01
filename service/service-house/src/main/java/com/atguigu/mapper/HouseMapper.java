package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.House;
import com.atguigu.entity.bo.HouseQueryBo;
import com.atguigu.entity.vo.HouseVo;
import com.github.pagehelper.Page;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : HouseMapper.java
 * @createTime : 2022/8/26 16:33
 * @Email :851185679@qq.com
 * @Description :
 */

public interface HouseMapper extends BaseMapper<House> {
    Page<HouseVo> findListPage(HouseQueryBo houseQueryBo);
}
