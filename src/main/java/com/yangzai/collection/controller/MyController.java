package com.yangzai.collection.controller;

import com.yangzai.collection.entity.User;
import com.yangzai.collection.mapper.UserMapper;
import com.yangzai.collection.vo.UserBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/26 14:11
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MyController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/selectOne")
    public ResponseEntity<User> selectOne(
            @ModelAttribute @Validated UserBody user){
//        if(0==0){
//            throw new RuntimeException();
//        }
        User user1 = userMapper.selectUser(user.getName(), user.getPassword());
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user1, HttpStatus.OK);
        return userResponseEntity;
    }
}
