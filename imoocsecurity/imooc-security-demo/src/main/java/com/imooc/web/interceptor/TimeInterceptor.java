package com.imooc.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
//@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info ("preHandle run, ClassName : {}, MethodName : {}"
                , ((HandlerMethod)o).getBean ().getClass ().getName ()
                , ((HandlerMethod)o).getMethod ().getName ());
        httpServletRequest.setAttribute ("startTime",new Date ().getTime ());
        return true;//控制后面的方法是否执行
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        long start = (long)httpServletRequest.getAttribute ("startTime");
        log.info ("postHandle , time interceptor waste time : {}"
                ,new Date ().getTime () - start);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long start = (long)httpServletRequest.getAttribute ("startTime");
        log.info ("afterCompletion, time interceptor waste time : {}"
                ,new Date ().getTime () - start);
        log.warn ("ex : {}",e);
    }
}
