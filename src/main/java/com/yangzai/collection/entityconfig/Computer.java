package com.yangzai.collection.entityconfig;

import com.yangzai.collection.entity.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/12 17:36
 * @Version 1.0
 */
@Configuration
public class Computer {
    @Bean(name = "123")
    public Person computer1(){
        return Person.builder().id(3).password("123").build();
    }

    @ConditionalOnMissingBean(name="456")
    @Bean("456")
    public Person computer2(){
        return Person.builder().id(4).password("456").build();
    }

}
