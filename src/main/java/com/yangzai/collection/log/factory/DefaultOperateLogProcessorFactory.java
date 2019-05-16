package com.yangzai.collection.log.factory;

import com.yangzai.collection.log.OperateLogProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 15:23
 * @Version 1.0
 */
@Service
public class DefaultOperateLogProcessorFactory implements OperateLogProcessorFactory,BeanPostProcessor {
    private Map<Integer,OperateLogProcessor> processorMap=new ConcurrentHashMap(16);
    @Override
    public OperateLogProcessor operateLogProcessor(int type) {
        return processorMap.get(type);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof OperateLogProcessor) {
            this.processorMap.put(((OperateLogProcessor) bean).getOperateLogType(), (OperateLogProcessor) bean);
        }
        return bean;
    }
}
