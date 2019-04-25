package com.yangzai.collection.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/1 10:43
 * @Version 1.0
 */
@Data
@ToString
public class User {
    private Integer id;
    private String name;
    private String password;
    private List<Role> userRoleId;
}
