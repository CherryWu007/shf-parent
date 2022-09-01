package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //启用SpringSecurity的默认行为
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用Controller方法上的安全注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //指定用户1
//        auth.inMemoryAuthentication().withUser("zhangsan") //用户名
//                .password(new BCryptPasswordEncoder().encode("123456")) //密码
//                .roles(""); //登录成功后的角色列表
//        //指定用户2
//        auth.inMemoryAuthentication().withUser("lisi")
//                .password(new BCryptPasswordEncoder().encode("654321"))
//                .roles("ADMIN","MEMORY"); //不需要手动的写ROLE_
//    }
    @Bean //创建对象并放入IoC容器
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //这个语句先不要删除，后面会删除
        //super.configure(http);
        //启用iframe可以访问
        http.headers().frameOptions().sameOrigin();
        //要求静态资源不经过认证即可访问
        http.authorizeRequests()
                .antMatchers("/static/**","/loginPage").permitAll() //静态资源直接放行,自定义登录页面需要放行
                //.antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/adfadf/adfa/**").hasAuthority("delete")
                .anyRequest().authenticated(); //其他资源必须认证（登录）才可以访问
        //自定义登录页面
        http.formLogin()
                .loginPage("/loginPage")
                //.usernameParameter("username")
                //.passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/");
        //自定义登出功能
        http.logout()
                .logoutUrl("/logout3")
                .logoutSuccessUrl("/loginPage")
                .invalidateHttpSession(true);
        //如果自定义登出功能，必须禁用csrf
        http.csrf().disable();

        //配置自定义访问拒绝处理器
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeineHandler());
    }
}
