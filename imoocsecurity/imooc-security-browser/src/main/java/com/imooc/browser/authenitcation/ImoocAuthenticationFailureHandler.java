package com.imooc.browser.authenitcation;

import com.imooc.browser.support.SimpleResponse;
import com.imooc.security.LoginType;
import com.imooc.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("ImoocAuthenticationFailureHandler")
public class ImoocAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper ();

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info ("登陆失败");
        if(LoginType.JSON.equals (securityProperties.getBrowser ().getLoginType ())) {
            httpServletResponse.setStatus (HttpStatus.INTERNAL_SERVER_ERROR.value ());
            httpServletResponse.setContentType ("application/json;charset=UTF-8");
            httpServletResponse.getWriter ().write (objectMapper.writeValueAsString (new SimpleResponse (e.getMessage ())));
        }else {
            super.onAuthenticationFailure (httpServletRequest,httpServletResponse,e);
        }
    }
}
