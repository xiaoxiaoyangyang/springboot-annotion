package com.yangzai.collection.log;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/16 15:08
 * @Version 1.0
 */
public interface OperateLogProcessor {
    /**
     * 获取操作日志的类型
     * @return
     */
    int getOperateLogType();
    /**
     * 执行插入操作日志处理
     *
     * @param request
     * @throws IOException
     */
    void process(HttpServletRequest request);

    /**
     * 执行插入操作日志处理
     *
     * @param accessToken
     * @param o
     */
    void process(String accessToken, Object o);

    /**
     * 请求预处理参数
     *
     * @param request
     */
    default void preHandle(HttpServletRequest request) {
    }
}
