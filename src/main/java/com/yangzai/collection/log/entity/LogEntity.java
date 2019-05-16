package com.yangzai.collection.log.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 15:30
 * @Version 1.0
 */
@Data
public class LogEntity {
    private Integer logId;
    private String logDesc;
    private Date createTime;
    private Integer userId;
    private Integer moduleId;
    private String moduleName;
}
