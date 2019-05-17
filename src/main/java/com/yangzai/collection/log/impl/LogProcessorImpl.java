package com.yangzai.collection.log.impl;

import com.yangzai.collection.log.OperateLogProcessor;
import com.yangzai.collection.log.factory.AbstractOperateLogProcessor;
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
public class LogProcessorImpl extends AbstractOperateLogProcessor {


    @Override
    public int getOperateLogType() {
        return 0;
    }

    @Override
    public void process(HttpServletRequest request) {

    }

    @Override
    public void process(String accessToken, Object o) {

    }

}
