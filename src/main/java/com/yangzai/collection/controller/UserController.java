package com.yangzai.collection.controller;

import com.yangzai.collection.annotation.LogAnnotation;
import com.yangzai.collection.annotation.LogTestAnnation;
import com.yangzai.collection.aop.MyTest;
import com.yangzai.collection.entity.User;
import com.yangzai.collection.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/15 10:31
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpServletRequest request;
    @GetMapping("selectOne")
//    @LogAnnotation(name = "用户登陆",userId = 1,moduleId = 1,moduleName = "用户模块")
    @LogTestAnnation(serviceId = 1)
    public ResponseEntity<User> selectOne(
            @RequestHeader(value = "userId")Integer userId,
            @ModelAttribute User user) throws InterruptedException {
        User user1 = userMapper.selectUser(user.getName(), user.getPassword());
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user1, HttpStatus.OK);
        return userResponseEntity;
    }

    @DeleteMapping("delete/userId")
//    @LogAnnotation(name = "删除用户")
    public ResponseEntity<Integer> delete(@PathVariable(value = "userId")Integer userId){
        Integer integer = userMapper.deleteUser(userId);
        return new ResponseEntity<>(integer,HttpStatus.OK);
    }
}
