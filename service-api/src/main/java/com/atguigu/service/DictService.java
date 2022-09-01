package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service
 * @ClassName : DictService.java
 * @createTime : 2022/8/24 20:20
 * @Email :851185679@qq.com
 * @Description :
 */

public interface DictService extends BaseService<Dict> {
    /**
     * 根据父id获取下级元素，每个元素转换为指定的json格式
     * Map <String,Object>  -----json字符串
     * @param parentId
     * @return
     */
    List<Map<String,Object>> findZnodes(Long parentId);

    /**
     * 根据编码获取子节点数据列表
     * @param dictCode
     * @return
     */
    List<Dict> findListByDictCode(String dictCode);

    List<Dict> findListByParentId(Long parentId);

    String getNameById(Long dictId);
}
