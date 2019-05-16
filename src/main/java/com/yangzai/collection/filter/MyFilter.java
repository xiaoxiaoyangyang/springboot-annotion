package com.yangzai.collection.filter;

import com.yangzai.collection.wrapper.BodyReaderHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/28 14:10
 * @Version 1.0
 */
@Slf4j
@Configuration
@WebFilter(urlPatterns = "/**",filterName = "myTestFilter")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        BodyReaderHttpServletRequestWrapper bodyReaderHttpServletRequestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        String requestURI = bodyReaderHttpServletRequestWrapper.getRequestURI();
        Enumeration<String> parameterName= bodyReaderHttpServletRequestWrapper.getParameterNames();
        Map<String, String> map = new HashMap<>();
        while (parameterName.hasMoreElements()){
            String s = parameterName.nextElement();
            String parameter = bodyReaderHttpServletRequestWrapper.getParameter(s);
            map.put(s,parameter);
        }
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("请求路径----------->[{}],请求的参数-------------->[{}]",requestURI,map.toString());
    }
}
