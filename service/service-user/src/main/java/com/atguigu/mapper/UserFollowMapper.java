package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;
import com.atguigu.entity.vo.UserFollowVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : UserFollowMapper.java
 * @createTime : 2022/8/30 22:22
 * @Email :851185679@qq.com
 * @Description :
 */

public interface UserFollowMapper extends BaseMapper<UserFollow> {
    int isFollow(@Param("houseId") Long houseId,@Param("userId") Long userId);

    Page<UserFollowVo> findListPage(Long userId);
}
