package com.imooc.web.vaildate;

import com.imooc.security.vaildateCode.ImageCode;
import com.imooc.security.vaildateCode.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author ：drp
 * @date ：Created in 2019/6/6 12:01
 * @description：
 * @modified By：
 * @version: $
 */
@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println ("更高级的图形验证码");
        return null;
    }
}
