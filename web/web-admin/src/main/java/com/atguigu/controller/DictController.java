package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : DictController.java
 * @createTime : 2022/8/24 21:50
 * @Email :851185679@qq.com
 * @Description :
 */
@Controller
@RequestMapping("/dict")
public class DictController {

    @Reference
    private DictService dictService;
    @RequestMapping("/findZnodes")
    @ResponseBody
    public Result findByParentId(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        List<Map<String, Object>> znodes = this.dictService.findZnodes(parentId);

        return Result.ok(znodes);
    }

    @RequestMapping
    public String toIndex(){
        return "dict/index";
    }

    /**
     * 根据上级id获取子节点数据列表
     * @param parentId
     * @return
     */
    @GetMapping(value = "findListByParentId/{parentId}")
    @ResponseBody
    public Result<List<Dict>> findListByParentId(@PathVariable Long parentId) {
        List<Dict> list = dictService.findListByParentId(parentId);
        return Result.ok(list);
    }

    /**
     * 根据编码获取子节点数据列表
     * @param dictCode
     * @return
     */
    @GetMapping(value = "findListByDictCode/{dictCode}")
    @ResponseBody
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findListByDictCode(dictCode);
        return Result.ok(list);
    }

}
