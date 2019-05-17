package com.yangzai.collection.mapper;

import com.yangzai.collection.entity.Org;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/17 13:57
 * @Version 1.0
 */
@Mapper
public interface OrgMapper {
    List<Org> selectAll();
}
