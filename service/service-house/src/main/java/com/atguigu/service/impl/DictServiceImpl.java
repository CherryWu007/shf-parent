package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Dict;
import com.atguigu.mapper.DictMapper;
import com.atguigu.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.service.impl
 * @ClassName : DictServiceImpl.java
 * @createTime : 2022/8/24 20:21
 * @Email :851185679@qq.com
 * @Description :
 */
@Service(interfaceClass = DictService.class)
@Transactional
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    @Autowired
    private DictMapper dictMapper;


    @Override
    public BaseMapper<Dict> getEntityDao() {
        return dictMapper;
    }
/**
*
*
         [{
        	id: '031',
        	name: 'n3.n1',
        	isParent: true
        }, {
        	id: '032',
        	name: 'n3.n2',
        	isParent: false
        }, {
        	id: '033',
        	name: 'n3.n3',
        	isParent: true
        }, {
        	id: '034',
        	name: 'n3.n4',
        	isParent: false
        }]
*
* */
    @Override
    public List<Map<String, Object>> findZnodes(Long parentId) {
        //访问数据库获取指定的字典项信息
        List<Dict> dictList= this.dictMapper.findListByParentId(parentId);
        //将List<Dict>转换为List<Map<String,Object>>
        List<Map<String, Object>> zNodes=new ArrayList<>();
        for (Dict dict:dictList){
            Map<String, Object> zNode=new HashMap<>();
            /*
            *
            *
            * {
            * id: '032',
        	* name: 'n3.n2',
        	* isParent: false
            * }
            *
            * */
            zNode.put("id",dict.getId());
            zNode.put("name",dict.getName());
            //判断该节点是否有下级节点
            Integer count= this.dictMapper.countIsParent(dict.getId());
            zNode.put("isParent", count > 0);

            zNodes.add(zNode);
        }
        //返回List<Map<String,Object>>
        return zNodes;//????
    }

    @Override
    public List<Dict> findListByDictCode(String dictCode) {

        //根据DictCode查找Dict
        Dict dict = dictMapper.getByDictCode(dictCode);
        if (null == dict) {
            return null;

        }
        return this.dictMapper.findListByParentId(dict.getId());
    }

    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return this.dictMapper.findListByParentId(parentId);
    }

    @Override
    public String getNameById(Long dictId) {
        return this.dictMapper.getNameById(dictId);
    }
}
