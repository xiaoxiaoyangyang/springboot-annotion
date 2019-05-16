package com.yangzai.collection.log.interceptor;

import com.yangzai.collection.annotation.LogAnnotation;
import com.yangzai.collection.log.entity.LogEntity;
import com.yangzai.collection.mapper.OperateLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 13:38
 * @Version 1.0
 */
public class OperateLogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("*************************");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("--------------->");
        super.postHandle(request, response, handler, modelAndView);
    }
}
