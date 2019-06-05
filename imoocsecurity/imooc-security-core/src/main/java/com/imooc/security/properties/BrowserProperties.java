package com.imooc.security.properties;

import com.imooc.security.LoginType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrowserProperties {
    private String loginPage = "/login.html";
    private LoginType loginType = LoginType.JSON;
}
