package com.example.miaosha.service;

import com.example.miaosha.service.model.UserModel;

public interface UserService {
    //通过用户id获取用户对象
    UserModel getUserById(Integer id);
}