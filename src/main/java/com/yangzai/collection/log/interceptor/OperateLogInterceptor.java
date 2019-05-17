package com.yangzai.collection.log.interceptor;

import com.yangzai.collection.annotation.LogAnnotation;
import com.yangzai.collection.annotation.LogTestAnnation;
import com.yangzai.collection.log.OperateLogProcessor;
import com.yangzai.collection.log.entity.LogEntity;
import com.yangzai.collection.log.factory.OperateLogProcessorFactory;
import com.yangzai.collection.mapper.OperateLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 13:38
 * @Version 1.0
 */

public class OperateLogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private OperateLogProcessorFactory operateLogProcessorFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LogTestAnnation annotation = method.getAnnotation(LogTestAnnation.class);
            OperateLogProcessor operateLogProcessor = operateLogProcessorFactory.operateLogProcessor(annotation.serviceId());
            operateLogProcessor.preHandle(request);

        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        // 处理操作日志注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LogTestAnnation annotation = handlerMethod.getMethodAnnotation(LogTestAnnation.class);
        if (annotation != null) {
            OperateLogProcessor processor = this.operateLogProcessorFactory.operateLogProcessor(annotation.serviceId());
            processor.process(request);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
