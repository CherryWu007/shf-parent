package com.atguigu.interceptor;

import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.util.WebUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created with IntelliJ IDEA.
 *
 * @author : 老贼
 * @version : 1.0
 * @Project : shf-parent
 * @Package : com.atguigu.interceptor
 * @ClassName : LoginInterceptor.java
 * @createTime : 2022/8/30 23:39
 * @Email :851185679@qq.com
 * @Description :前端登录拦截器
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        //判断用户是否已经登录
        Object userInfo = request.getSession().getAttribute("USER");
        //如果没有登录，返回208的Result响应
        if(userInfo==null){ //没有登录
            Result result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
            WebUtil.writeJSON(response,result);
            return false;
        }
        return true;
    }

}
