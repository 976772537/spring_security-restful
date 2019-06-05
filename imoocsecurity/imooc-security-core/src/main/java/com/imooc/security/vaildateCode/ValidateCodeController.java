package com.imooc.security.vaildateCode;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：drp
 * @date ：Created in 2019/6/5 18:35
 * @description：ValidateCode
 * @modified By：
 * @version: 1.0$
 */
@RestController
public class ValidateCodeController {

    private static  final  String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy ();

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ImageCode imageCode = createImageCode(request);
        sessionStrategy.setAttribute (new ServletWebRequest (request),SESSION_KEY,imageCode);
        ImageIO.write (imageCode.getImage (),"JPEG",response.getOutputStream ());
    }

    private ImageCode createImageCode(HttpServletRequest request) {
        //create Image code ..
        return null;
    }
}
