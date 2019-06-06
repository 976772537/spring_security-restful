package com.imooc.security.vaildateCode;

import com.imooc.security.properties.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author ：drp
 * @date ：Created in 2019/6/6 11:45
 * @description：
 * @modified By：
 * @version: 1.0$
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {
    @Getter
    @Setter
    @Autowired
   private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        //..
        return null;
    }
}
