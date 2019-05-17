package com.yangzai.collection.annotation;

import java.lang.annotation.*;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 13:42
 * @Version 1.0
 */
@Documented
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    String name() default "";
    int userId() default 0;
    int moduleId() default 0;
    String moduleName() default "";
}
