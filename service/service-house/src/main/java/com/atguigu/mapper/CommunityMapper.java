package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Community;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : CommunityMapper.java
 * @createTime : 2022/8/25 0:31
 * @Email :851185679@qq.com
 * @Description :
 */

public interface CommunityMapper extends BaseMapper<Community> {

    List<Community> findAll();
}
