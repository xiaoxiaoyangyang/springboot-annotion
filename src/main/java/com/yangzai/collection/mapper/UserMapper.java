package com.yangzai.collection.mapper;

import com.yangzai.collection.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/1 10:47
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    User selectUser(@Param(value = "name")String name, @Param(value = "password")String password);

    Integer deleteUser(Integer userId);

    User selectByUserId(Integer userId);
}
