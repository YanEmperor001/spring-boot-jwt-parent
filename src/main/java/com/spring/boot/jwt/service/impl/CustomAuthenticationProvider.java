package com.spring.boot.jwt.service.impl;

import com.spring.boot.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        //认证逻辑
        UserDetails userDetails = userService.verifiUser(username);
        if (null != userDetails) {
            String encodePassword = DigestUtils.md5DigestAsHex(password.getBytes());
            if (userDetails.getPassword().equals(encodePassword)) {
                //设置权限和角色
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
                authorities.add(new GrantedAuthorityImpl("AUTH_WRITE"));
                //生成令牌
                Authentication auth = new UsernamePasswordAuthenticationToken(username, password, authorities);
                return auth;
            } else {
                throw new BadCredentialsException("密码错误！");
            }
        } else {
            throw new UsernameNotFoundException("用户不存在！");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
