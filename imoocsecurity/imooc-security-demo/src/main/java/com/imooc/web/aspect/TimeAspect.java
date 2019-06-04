package com.imooc.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Slf4j
//@Component
public class TimeAspect {

    @Around ("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info ("aspect time  start");
        long start = new Date ().getTime ();
        Arrays.stream (pjp.getArgs ()).forEach (o -> log.info ("args : {}",o));
        Object pro = pjp.proceed ();
        log.info ("time aspect waste time : {}",new Date ().getTime () - start);
        log.info ("aspect time  finish");
        return pro;
    }
}
