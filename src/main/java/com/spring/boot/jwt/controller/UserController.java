package com.spring.boot.jwt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.spring.boot.jwt.dao.UserMapper;
import com.spring.boot.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final Gson gson = new Gson();

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/search")
    public String searchUser() {

        PageHelper.startPage(1, 1, "id desc");
        List<User> userList = userMapper.searchUser();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return gson.toJson(pageInfo);
    }

    @RequestMapping("/insert")
    public void insert() {
        User user = new User();
        user.setUsername("guofeng");
        user.setPassword(DigestUtils.md5DigestAsHex(("123456").getBytes()));
        user.setAge(28);
        userMapper.insertSelective(user);
    }
}
