package com.yangzai.collection.log.impl;

import com.yangzai.collection.log.OperateLogProcessor;
import com.yangzai.collection.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 15:25
 * @Version 1.0
 */
@Service
public class LogProcessorImpl implements OperateLogProcessor {


    @Override
    public void process(HttpServletRequest request) {

    }
}
