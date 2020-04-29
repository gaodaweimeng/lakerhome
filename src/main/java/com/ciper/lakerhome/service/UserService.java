package com.ciper.lakerhome.service;

import com.ciper.lakerhome.entity.User;
import com.ciper.lakerhome.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    public UserMapper userMapper;
    private User user;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //登录
    public User Login(String email, String password){
        return userMapper.Login(email, password);
    }

    //修改个人信息
    public int UpdateUser(String email) {
        return userMapper.UpdateUser(user.getEmail());
    }

    //账号查询
    public User SelectByUserId(String email){
        return userMapper.SelectByUserId(email);
    }
}

