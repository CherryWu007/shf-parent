package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.HouseImage;
import com.atguigu.result.Result;
import com.atguigu.service.HouseImageService;
import com.atguigu.util.QiniuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : HouseImageController.java
 * @createTime : 2022/8/28 11:54
 * @Email :851185679@qq.com
 * @Description :
 */
@Controller
@RequestMapping("/houseImage")
public class HouseImageController extends BaseController {
    @Reference
    private HouseImageService houseImageService;


    /**
     * 新增房产图片信息
     * @param houseId
     * @param type
     * @return
     */
    @RequestMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(@PathVariable Long houseId, @PathVariable Integer type, Model model){
        model.addAttribute("houseId",houseId);
        model.addAttribute("type",type);

        return "house/upload";
    }

    @RequestMapping("/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(@PathVariable Long houseId, @PathVariable Integer type, Model model, @RequestParam("file") MultipartFile[] files) throws IOException {
        //判断上传图片数量是否为零
        if (files!=null && files.length>0){
            for (MultipartFile file:files){
                //上传图片至七牛云(避免同名文件覆盖，需要重新命名UUID)
                String originalFilename = file.getOriginalFilename();//指定图片
                String fileName = UUID.randomUUID().toString();//没有扩展名

                fileName +=originalFilename.substring(originalFilename.lastIndexOf("."));//设置后缀

                QiniuUtils.upload2Qiniu(file.getBytes(),fileName);
                //向hse_house_image表中添加一条记录
                HouseImage houseImage = new HouseImage();
                houseImage.setHouseId(houseId);
                houseImage.setImageName(fileName);
                houseImage.setImageUrl("http://rhbq9flmw.hb-bkt.clouddn.com/"+fileName);
                houseImage.setType(type);
                houseImageService.insert(houseImage);
            }
        }


        return Result.ok();
    }

    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable Long houseId, @PathVariable Long id){
        //删除七牛云上的图片
        HouseImage houseImage = this.houseImageService.getById(id);//通过图片id查询到图片的地址
        QiniuUtils.deleteFileFromQiniu(houseImage.getImageName());
        //删除数据库上的图片(假删除，仅为将图片状态加以更改)
        houseImageService.delete(id);

        return "redirect:/house/"+houseId;

    }
}
