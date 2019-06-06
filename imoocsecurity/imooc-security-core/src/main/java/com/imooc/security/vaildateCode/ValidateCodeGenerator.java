package com.imooc.security.vaildateCode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author ：drp
 * @date ：Created in 2019/6/6 11:40
 * @description： this interface for main of validateCode
 * @modified By：
 * @version: 1.0$
 */
public interface ValidateCodeGenerator {
    ImageCode generate(ServletWebRequest request);
}


