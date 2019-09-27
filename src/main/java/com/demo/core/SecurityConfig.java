package com.demo.core;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .addFilterBefore(new SecurityFilter(), BasicAuthenticationFilter.class) 
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
        .httpBasic()
        .and()
        .csrf()
        .disable()
        ;
    }

}
