package com.yangzai.collection.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/1 10:42
 * @Version 1.0
 */
@Data
@ToString
public class Role {
    private Integer id;
    private String roleName;
    private List<Permission> permissionList;
}
