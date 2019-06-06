package com.imooc.security.vaildateCode;

import com.imooc.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：drp
 * @date ：Created in 2019/6/6 11:51
 * @description：
 * @modified By：
 * @version: 1.0$
 */
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator ();
        codeGenerator.setSecurityProperties (securityProperties);
        return codeGenerator;
    }
}
