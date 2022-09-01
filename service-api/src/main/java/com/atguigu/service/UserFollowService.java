package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;
import com.atguigu.entity.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service
 * @ClassName : UserFollowService.java
 * @createTime : 2022/8/30 22:24
 * @Email :851185679@qq.com
 * @Description :
 */

public interface UserFollowService extends BaseService<UserFollow> {
    void follow(UserFollow userFollow);

    boolean isFollow(Long houseId, Long UserId);

    PageInfo<UserFollowVo> findListPage(Integer pageNum, Integer pageSize, Long id);
}
