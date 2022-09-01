package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.UserInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : UserInfoMapper.java
 * @createTime : 2022/8/30 16:11
 * @Email :851185679@qq.com
 * @Description :
 */

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserInfo getByPhone(String phone);
}
