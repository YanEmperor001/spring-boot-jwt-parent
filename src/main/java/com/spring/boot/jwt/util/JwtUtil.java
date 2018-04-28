package com.spring.boot.jwt.util;


import org.springframework.util.StringUtils;

public class JwtUtil {

    private static final String AUTHORIZATION_HEADER_PREFIX = "USER_";

    public static String getToken(String authorizationHeader) {
        return authorizationHeader.substring(AUTHORIZATION_HEADER_PREFIX.length());
    }

    public static String getTokenHeander(String token) {
        return AUTHORIZATION_HEADER_PREFIX + token;
    }

    public static boolean validate(String authorizationHeader) {
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(AUTHORIZATION_HEADER_PREFIX);
    }

    public static String getAuthorizationHeaderPrefix() {
        return AUTHORIZATION_HEADER_PREFIX;
    }
}
