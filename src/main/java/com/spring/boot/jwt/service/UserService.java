package com.spring.boot.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails verifiUser(String username);
}
