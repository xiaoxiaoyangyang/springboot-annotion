package com.yangzai.collection.pojo.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/26 17:48
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "")
@PropertySource(value = {"classpath:config/ValidationMessages.properties"},encoding = "UTF-8")
public class ErrorMessage {
    private Map<String,String> code=new HashMap<>();
    private String name;
}
