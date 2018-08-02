package cn.fanrt.common.user.service;

import cn.fanrt.common.user.domain.SysUser;
import cn.fanrt.common.user.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-08-02 14:30
 **/
@Service
public class SysUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.sysUserRepository.findByAccount(username);
        if (sysUser != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            new User(sysUser.getAccount(), sysUser.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
}
