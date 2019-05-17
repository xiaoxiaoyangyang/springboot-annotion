package com.yangzai.collection.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/17 13:51
 * @Version 1.0
 */
@Data
@ToString
public class Org {
    private Integer orgId;
    private Integer parentId;
    private String orgName;
    private String treePath;
    private List<Org> list=new ArrayList<>()
;}
