package com.yangzai.collection.log.impl;

import com.yangzai.collection.entity.User;
import com.yangzai.collection.log.OperateLogProcessor;
import com.yangzai.collection.log.entity.LogEntity;
import com.yangzai.collection.mapper.OperateLogMapper;
import com.yangzai.collection.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 15:25
 * @Version 1.0
 */
@Service
public class LogProcessorImpl implements OperateLogProcessor {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public int getOperateLogType() {
        return 1;
    }


    @Override
    public void process(HttpServletRequest request) {
//        Map<?, ?> pathVariables = (Map<?, ?>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//        String userId = (String)pathVariables.get("userId");
        String userId = request.getHeader("userId");
        User user = userMapper.selectByUserId(Integer.parseInt(userId));
        process(user);
    }

    @Override
    public void process(Object o) {
        User o1 = (User) o;
        LogEntity logEntity = new LogEntity();
        logEntity.setModuleId(1);
        logEntity.setModuleName("用户模块");
        logEntity.setLogDesc("用户登陆");
        logEntity.setUserId(o1.getId());
        logEntity.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        operateLogMapper.insert(logEntity);
    }

}
