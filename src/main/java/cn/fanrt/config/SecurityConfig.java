package cn.fanrt.config;

import cn.fanrt.common.user.domain.SysUser;
import cn.fanrt.common.user.repository.SysUserRepository;
import cn.fanrt.common.user.service.SysUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-08-02 14:14
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public SysUserRepository sysUserRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/resource/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .usernameParameter("username")     //指定页面中对应用户名的参数名称
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/common/main.do")
                .failureUrl("/login.html?error=true")
                .permitAll()
                .and()
                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login.html")
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                        SysUser sysUser = sysUserRepository.findByAccount(username);
                        if (sysUser != null) {
                            List<GrantedAuthority> authorities = new ArrayList<>();
                            return new User(sysUser.getAccount(), sysUser.getPassword(), authorities);
                        }
                        throw new UsernameNotFoundException("User '" + username + "' not found.");
                    }
                }).passwordEncoder(new BCryptPasswordEncoder());
    }
}
