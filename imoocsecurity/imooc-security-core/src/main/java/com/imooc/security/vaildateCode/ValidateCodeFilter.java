package com.imooc.security.vaildateCode;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：drp
 * @date ：Created in 2019/6/5 19:02
 * @description： the filter for validate code
 * @modified By：
 * @version: 1.0$
 */
public class ValidateCodeFilter extends OncePerRequestFilter {
    @Getter
    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals ("/authentication/form", httpServletRequest.getRequestURI ())
        && StringUtils.equalsIgnoreCase (httpServletRequest.getMethod (),"post")){
            try{
                validate(new ServletWebRequest (httpServletRequest));
            }catch(ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure (httpServletRequest,httpServletResponse,e);
                return;
            }
        }
        filterChain.doFilter (httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest servletWebRequest){
        // validate code service..
    }
}
