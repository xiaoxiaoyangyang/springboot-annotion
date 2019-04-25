package com.yangzai.collection.controller;

import com.yangzai.collection.aop.MyTest;
import com.yangzai.collection.entity.User;
import com.yangzai.collection.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/15 10:31
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("selectOne")
    //@MyTest
    public ResponseEntity<User> selectOne(
            @ModelAttribute User user) throws InterruptedException {
        System.out.println("ni hao ya ");
        User user1 = userMapper.selectUser(user.getName(), user.getPassword());
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user1, HttpStatus.OK);
        System.out.println("zhe yang hao ma ");
        return userResponseEntity;
    }
}
