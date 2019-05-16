package com.yangzai.collection.entityconfig;

import com.yangzai.collection.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/12 17:09
 * @Version 1.0
 */
@Configuration
@Conditional({WindowsCondition.class})
public class BeanConfig {
    @Bean(name = "bill")
   // @Conditional({WindowsCondition.class})
    public Person person1(){

        return Person.builder().id(1).password("1231").build();
    }

    @Bean("linus")
  //  @Conditional({LinuxCondition.class})
    public Person person2(){
        return Person.builder().id(2).password("234556").build();
    }

}
