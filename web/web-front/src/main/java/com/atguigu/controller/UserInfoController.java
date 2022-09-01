package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.UserInfo;
import com.atguigu.entity.bo.LoginBo;
import com.atguigu.entity.bo.RegisterBo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.MD5;
import com.atguigu.util.ValidateCodeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.controller
 * @ClassName : UserInfoController.java
 * @createTime : 2022/8/30 16:15
 * @Email :851185679@qq.com
 * @Description :
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Reference
    private UserInfoService userInfoService;

    @RequestMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone, HttpServletRequest request){
        //生成验证码
        String validateCode = ValidateCodeUtils.generateValidateCode4String(6);
        //通过阿里短信发送到指定手机上（略）
        //将验证码保存在session中，注册时进行比较
        request.getSession().setAttribute("CODE",validateCode);
        //返回验证码
        return Result.ok(validateCode);

    }

    /**
     * 注册
     * @param registerBo
     * @return
     */
    @RequestMapping("/register")
    public Result submitRegister(@RequestBody RegisterBo registerBo,HttpServletRequest request){
        //从RegisterBo中获取成员变量
        String nickName = registerBo.getNickName();
        String phone = registerBo.getPhone();
        String password = registerBo.getPassword();
        String code = registerBo.getCode();

        //非空判断
        if (StringUtils.isEmpty(nickName)
                ||StringUtils.isEmpty(phone)
                ||StringUtils.isEmpty(password)
                ||StringUtils.isEmpty(code)){
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);//203
        }
        //验证是否正确
        Object validateCode=request.getSession().getAttribute("CODE");
        if (!code.equals(validateCode)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);//210
        }
        //判断该手机是否已经注册
        UserInfo userInfo= userInfoService.getByPhone(phone);
        if (userInfo!=null){
            return Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        //实现注册
        userInfo=new UserInfo();
        userInfo.setNickName(nickName);
        userInfo.setPhone(phone);
        userInfo.setStatus(1);//未禁用
        userInfo.setPassword(MD5.encrypt(password));




        userInfoService.insert(userInfo);
        return Result.ok();
    }


    @PostMapping("/login")
    public Result login(@RequestBody LoginBo loginBo,HttpServletRequest request){
        String phone = loginBo.getPhone();
        String password = loginBo.getPassword();

        //校验参数
        if(StringUtils.isEmpty(phone) ||
                StringUtils.isEmpty(password)) {
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }
        //按照手机号码查询用户
        UserInfo userInfo= userInfoService.getByPhone(phone);
        if (userInfo==null){
            return Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
        }
        //判断密码是否正确
        if (!MD5.encrypt(password).equals(userInfo.getPassword())){
            System.out.println(ResultCodeEnum.PASSWORD_ERROR);
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        //判断用户是否被禁用
        if (userInfo.getStatus()!=1){
            return Result.build(null,ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        //传统的转发情况下，可以将用户信息放入session中然后再跳转后页面中从session中获取用户信息
        //此处写该语句并不是要在页面上获取用户名，而是作为判断用户是否已经登录的标记，后面比如拦截器，过滤器中
        request.getSession().setAttribute("USER",userInfo);
        //现在是aJax请求，用户信息必须放到Result.ok(Data)中
        Map<String, Object> map=new HashMap<>();
        map.put("nickName",userInfo.getNickName());
        map.put("phone", userInfo.getPhone());
        return Result.ok(map);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        //request.getSession().removeAttribute("USER");//session不变
        request.getSession().invalidate();// 新的session
        return Result.ok();
    }

}
