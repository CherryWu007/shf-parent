package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Dict;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.mapper
 * @ClassName : DictMapper.java
 * @createTime : 2022/8/24 20:18
 * @Email :851185679@qq.com
 * @Description :
 */

public interface DictMapper extends BaseMapper<Dict> {
    List<Dict> findListByParentId(Long parentId);

    Integer countIsParent(Long id);

    String getNameById(Long id);

    Dict getByDictCode(String dictCode);


}
