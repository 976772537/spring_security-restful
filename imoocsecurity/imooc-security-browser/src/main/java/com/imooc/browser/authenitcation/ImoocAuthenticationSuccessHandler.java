package com.imooc.browser.authenitcation;

import com.imooc.security.LoginType;
import com.imooc.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private ObjectMapper objectMapper = new ObjectMapper ();

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info ("登陆成功");
        if(LoginType.JSON.equals (securityProperties.getBrowser ().getLoginType ())) {
            httpServletResponse.setContentType ("application/json;charset=utf-8");
            httpServletResponse.getWriter ().write (objectMapper.writeValueAsString (authentication));
        }else{
            super.onAuthenticationSuccess (httpServletRequest,httpServletResponse,authentication);
        }
    }
}
