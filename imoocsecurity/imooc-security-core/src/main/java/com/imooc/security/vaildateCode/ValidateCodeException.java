package com.imooc.security.vaildateCode;


import org.springframework.security.core.AuthenticationException;

/**
 * @author ：drp
 * @date ：Created in 2019/6/5 19:19
 * @description：
 * @modified By：
 * @version: 1.0$
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String explanation) {
        super (explanation);
    }
}

