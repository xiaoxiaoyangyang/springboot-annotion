package com.yangzai.collection.aop;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yangzai.collection.annotation.LogAnnotation;
import com.yangzai.collection.entity.User;
import com.yangzai.collection.mapper.OperateLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/12 18:01
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class MyAop {
    /**
     *切入点
     * @annotation(* com.yangzai.collection.aop.*.*(..))
     */
    @Pointcut("@annotation(com.yangzai.collection.annotation.LogAnnotation)")
    public void cotrollerAspect(){}

    @Autowired
    private OperateLogMapper operateLogMapper;

//    @Around("cotrollerAspect()")
//    public Object aroundAdvice1(ProceedingJoinPoint joinPoint) throws Throwable {
//        //获取全类名
//        String name = joinPoint.getTarget().getClass().getName();
//        log.info("controller name {}-------------->"+name);
//        Class<?> aClass = Class.forName(name);
//
//
//        //获取方法的名字
//        String name2 = joinPoint.getSignature().getName();
//        log.info("signature {}-----------------"+name2);
//
//
//        Object proceed = joinPoint.proceed();
//
//        //获取方法的注解上的属性
//        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
//        Method method2 = signature.getMethod();
//        MyTest annotation = method2.getAnnotation(MyTest.class);
//        int id = annotation.id();
//        String name3 = annotation.name();
//        log.info("id {}-------------------->"+id);
//        log.info("name3 {}---------------------->"+name3);
//        //获取方法中所所传递的参数的值
//        Object[] args = joinPoint.getArgs();
//        Map<String,Object> map=new HashMap();
//
//        Class<?>[] parameterTypes = method2.getParameterTypes();
//        for(Class ca:parameterTypes){
//            Field[] fields = ca.getDeclaredFields();
//            for(int i=0;i<parameterTypes.length;i++){
//                String name1 = fields[i].getName();
//                System.out.println(name1+"&&&&&&&&&&*************");
//            }
//        }
//
//        String method = request.getMethod();
//        log.info("method {}-------------------->"+method);
//        //获取httpServletRequest
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        HttpServletRequest servletRequest = requestAttributes.getRequest();
//        String method1 = servletRequest.getMethod();
//        log.info("method1 {}----------------------->"+method1);
//        //   /collection
//        String contextPath = servletRequest.getContextPath();
//        log.info("context path {}----------------------->"+contextPath);
//        //  /selectOne
//        String servletPath = servletRequest.getServletPath();
//        log.info("servlet path {}---------------->"+servletPath);
//        // /collection/selectOne
//        String requestURI = servletRequest.getRequestURI();
//        log.info("request uri {}------------------>"+requestURI);
//        // http://localhost:8080/collection/selectOne
//        StringBuffer requestURL = servletRequest.getRequestURL();
//        log.info("request url {}------------------->"+requestURL);
//        return proceed;
//    }
    @Around("cotrollerAspect()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        Class<?> aClass = joinPoint.getTarget().getClass();

        String clazzName = clazz.getName();
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //参数
        Object[] args = joinPoint.getArgs();

        Map<String, Object> map = getFieldsName(this.getClass(), clazzName, methodName, args);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method1 = signature.getMethod();
        LogAnnotation annotation = method1.getAnnotation(LogAnnotation.class);


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String methodType = request.getMethod();
        Method method = signature.getMethod();
        log.info("request start --> {}.{} : parameter:{}|| ReqType :{}", method.getDeclaringClass().getName(), method.getName(), map, methodType);
        long start = System.currentTimeMillis();

        //执行
        ResponseEntity result = (ResponseEntity)joinPoint.proceed();
        long timeConsuming = System.currentTimeMillis() - start;
        log.info("request end --> return:{} time consuming:{}ms || ReqType :{}", JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue), timeConsuming, methodType);
        return result;
    }

    public Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            ClassPool pool = ClassPool.getDefault();
            ClassClassPath classPath = new ClassClassPath(cls);
            pool.insertClassPath(classPath);

            CtClass cc = null;

            cc = pool.get(clazzName);
            CtMethod cm = cc.getDeclaredMethod(methodName);
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (attr == null) {
                throw new RuntimeException();
            }
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < cm.getParameterTypes().length; i++) {
                map.put(attr.variableName(i + pos), args[i]);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
