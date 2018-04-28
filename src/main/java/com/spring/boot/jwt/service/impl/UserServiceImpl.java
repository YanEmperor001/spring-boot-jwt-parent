package com.spring.boot.jwt.service.impl;

import com.spring.boot.jwt.dao.UserMapper;
import com.spring.boot.jwt.entity.User;
import com.spring.boot.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails verifiUser(String username) {

        User user = userMapper.selectByName(username);

        if (null == user) {
            throw new UsernameNotFoundException("用户不存在！");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
