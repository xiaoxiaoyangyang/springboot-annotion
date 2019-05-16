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
     * 执行插入操作日志处理
     *
     * @param request
     * @throws IOException
     */
    void process(HttpServletRequest request);
}
