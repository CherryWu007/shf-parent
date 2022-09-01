package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.UserInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service
 * @ClassName : UserInfoService.java
 * @createTime : 2022/8/30 16:12
 * @Email :851185679@qq.com
 * @Description :
 */

public interface UserInfoService extends BaseService<UserInfo> {
    UserInfo getByPhone(String phone);
}
