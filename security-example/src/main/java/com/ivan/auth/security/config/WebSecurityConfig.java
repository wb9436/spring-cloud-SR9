package com.ivan.auth.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: WB
 * @version: v1.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 注意: configure方法中的参数http 需要是HttpSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  表单登录
        http.formLogin()
                // 自定义登录页面
                .loginPage("/login.html")
                // 自定义登录逻辑,login.html页面的请求发送过来，由下面的的登录逻辑接收，转而去执行 UserServiceImp实现类
                //  而与Controller中的login请求无关
                .loginProcessingUrl("/login")
                // 登录成功后 跳转页面，必须是POST请求
                .successForwardUrl("/toMain");
        // 授权
        http.authorizeRequests()
                // 放行登录请求,如果不放行，就会出现重定向次数的过多的情况(会一直重定向到login.html)
                .antMatchers("/login.html").permitAll()
                // 让所有请求被认证(登录)之后  才可以访问
                .anyRequest().authenticated();

        // csrf: 可以理解为防火墙，现 关闭 csrf
        http.csrf().disable();
    }

}
