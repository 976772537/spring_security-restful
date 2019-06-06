package com.imooc.security.vaildateCode;

import com.imooc.security.properties.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ：drp
 * @date ：Created in 2019/6/5 19:02
 * @description： the filter for validate code
 * @modified By：
 * @version: 1.0$
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    @Getter
    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;

    private Set<String> urls = new HashSet<>();

    @Getter
    @Setter
    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher ();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet ();
        String[] configUrls = StringUtils.split (securityProperties.getCode ().getImage ().getUrl ());
        for (String configUrl : configUrls){
            urls.add (configUrl);
        }
        urls.add ("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for(String url : urls){
            if(pathMatcher.match (url,httpServletRequest.getRequestURI ())){
                action = true ;
                break;
            }
        }
        if(action){
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
