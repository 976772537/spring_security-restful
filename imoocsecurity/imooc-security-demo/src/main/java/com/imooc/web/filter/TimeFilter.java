package com.imooc.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class TimeFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info ("TimeFilter init..");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       log.info ("Time Filter start");
        long start = new Date ().getTime ();
        filterChain.doFilter (servletRequest,servletResponse);
        log.warn ("time filter :" +(new Date ().getTime () - start));
        log.info("Time Filter finish");
    }

    @Override
    public void destroy() {
        log.info ("Time Filter destroy");
    }
}
