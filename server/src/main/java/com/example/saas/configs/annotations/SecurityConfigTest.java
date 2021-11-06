package com.example.saas.configs.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // 开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启Spring方法级安全
@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;

    // 自定义认证配置
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String code = encoder.encode("123");

        auth.userDetailsService(userDetailsService).passwordEncoder(password());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginProcessingUrl("/user/login").defaultSuccessUrl("/home").permitAll().and()
                .authorizeRequests().antMatchers("/", "/index", "/user/login").permitAll().and().csrf().disable();

        http.authorizeRequests().antMatchers("/user/login", "/swagger-ui.html").permitAll();
//        http.authorizeRequests().antMatchers("/api/**/**").authenticated();
        http.csrf().disable();// 禁用csrf校验

    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

}
