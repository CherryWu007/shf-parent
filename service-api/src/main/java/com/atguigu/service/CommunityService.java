package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Community;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service
 * @ClassName : CommunityService.java
 * @createTime : 2022/8/25 0:34
 * @Email :851185679@qq.com
 * @Description :
 */

public interface CommunityService extends BaseService<Community> {
    List<Community> findAll();
}
