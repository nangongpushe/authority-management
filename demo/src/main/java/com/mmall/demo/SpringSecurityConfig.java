package com.mmall.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * Created by jimin on 2017/8/24.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("zhangsan").password("zhangsan").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("demo").password("demo").roles("USER");

//        使用自己定义的管理数据库用户的类，以及自定义的验证器，需要用到数据库，下面注释掉，以免报错
//        auth.userDetailsService(myUserService).passwordEncoder(new MyPasswordEncoder());

//        使用自己的查询user的方法，还可以指定根据权限去查询，还可以支持密码认证的方法，需要用到数据库，下面注释掉，以免报错
//        auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(new MyPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll() //项目的主路径是允许访问的
                .anyRequest().authenticated() //项目的其他路径全部要经过验证
                .and()
                .logout().permitAll() //注销功能不需要验证
                .and()
                .formLogin();//支持表单登录
        http.csrf().disable(); //阻止其默认的认证方式
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        忽略掉这些路径上的权限拦截
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }
}
