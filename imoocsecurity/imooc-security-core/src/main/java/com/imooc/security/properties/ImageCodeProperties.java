package com.imooc.security.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：drp
 * @date ：Created in 2019/6/6 11:02
 * @description：
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCodeProperties {
    private int width = 67;
    private int height =23;
    private int length = 4; //show num of world
    private int expireIn = 60;
    private String url;
}
