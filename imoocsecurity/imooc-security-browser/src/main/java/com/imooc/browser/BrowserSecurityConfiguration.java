package com.imooc.browser;

import com.imooc.browser.authenitcation.ImoocAuthenticationSuccessHandler;
import com.imooc.security.properties.SecurityProperties;
import com.imooc.security.vaildateCode.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter ();
        validateCodeFilter.setAuthenticationFailureHandler (authenticationFailureHandler);
        http.addFilterBefore (validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin ()
                .loginPage ("/authentication/require")
                .loginProcessingUrl ("/authentication/form")
                .successHandler (authenticationSuccessHandler)
                .failureHandler (authenticationFailureHandler)
                .and ()
                .authorizeRequests ()
                .antMatchers ("/authentication/require",
                        securityProperties.getBrowser ().getLoginPage ())
                .permitAll ()
                .anyRequest ()
                .authenticated ()
                .and()
                .csrf ().disable ();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder ();
    }
}
