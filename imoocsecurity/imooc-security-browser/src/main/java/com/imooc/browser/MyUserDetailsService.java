package com.imooc.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info ("登陆用户名:{}",username);
//        return new User (username,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList ("admin"));
        String password = passwordEncoder.encode ("123456");
        log.info ("登录用户的密码:{}",password);
        return new User (username,password
                ,true,true,true,true
                ,AuthorityUtils.commaSeparatedStringToAuthorityList ("admin"));
    }


}
