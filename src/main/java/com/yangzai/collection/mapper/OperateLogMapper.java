package com.yangzai.collection.mapper;

import com.yangzai.collection.log.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 15:40
 * @Version 1.0
 */
@Mapper
public interface OperateLogMapper {
    Integer insert(LogEntity logEntity);
}
