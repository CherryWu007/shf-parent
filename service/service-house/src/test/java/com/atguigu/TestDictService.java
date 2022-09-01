package com.atguigu;

import com.atguigu.service.DictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.test
 * @ClassName : com.atguigu.TestDictService.java
 * @createTime : 2022/8/24 21:14
 * @Email :851185679@qq.com
 * @Description :
 */
@SpringJUnitConfig(locations = "classpath:/spring/spring-*.xml")
public class TestDictService {
    @Autowired
    private DictService dictService;

    @Test
    public void testFindZnodes(){
        //List<Map<String, Object>> znodes = this.dictService.findZnodes(0L);
        List<Map<String, Object>> znodes = this.dictService.findZnodes(0L);
        //List<Map<String, Object>> znodes = this.dictService.findZnodes(0L);
        znodes.forEach(System.out::println);
    }
}
