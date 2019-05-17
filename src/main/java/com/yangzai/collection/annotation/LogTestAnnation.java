package com.yangzai.collection.annotation;

import java.lang.annotation.*;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/17 11:01
 * @Version 1.0
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogTestAnnation {
    int serviceId() default 0;
}
