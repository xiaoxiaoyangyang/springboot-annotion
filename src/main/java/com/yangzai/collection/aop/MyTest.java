package com.yangzai.collection.aop;

import java.lang.annotation.*;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/12 18:01
 * @Version 1.0
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest {
    String name() default "";
    String className() default "";
    int id() default 0;
}
