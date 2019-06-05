package com.imooc.browser;

import com.imooc.browser.support.SimpleResponse;
import com.imooc.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache ();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy ();

    @Autowired
    private SecurityProperties securityProperties;

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @RequestMapping("/authentication/require")
    public SimpleResponse requireAuthentication(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest (req,resp);

        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl ();
            log.info ("引发跳转的请求是： {}"+targetUrl);
            if(StringUtils.endsWithIgnoreCase (targetUrl,".html")){
                redirectStrategy.sendRedirect (req,resp,securityProperties.getBrowser ().getLoginPage ());
            }
        }
        return new SimpleResponse ("访问的服务需要身份认证，请引导至登陆页");
    }

}
