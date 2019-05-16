package com.yangzai.collection.log.factory;

import com.yangzai.collection.log.OperateLogProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 15:23
 * @Version 1.0
 */
@Service
public class DefaultOperateLogProcessorFactory implements OperateLogProcessorFactory,BeanPostProcessor {
    @Override
    public OperateLogProcessor operateLogProcessor() {
        return null;
    }
}
