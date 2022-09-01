package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : DictController.java
 * @createTime : 2022/8/29 17:01
 * @Email :851185679@qq.com
 * @Description :
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Reference
    private DictService dictService;

    @RequestMapping("/findListByDictCode/{dictCode}")
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode){
        List<Dict> dictList = this.dictService.findListByDictCode(dictCode);
        return Result.ok(dictList);
    }


    @RequestMapping("/findListByParentId/{parenId}")
    public Result<List<Dict>> findListByParentId(@PathVariable Long parenId){
        List<Dict> dictList = this.dictService.findListByParentId(parenId);
        return Result.ok(dictList);
    }

}
