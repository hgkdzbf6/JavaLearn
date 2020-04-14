package com.example.miaosha.service;

import com.example.miaosha.error.BusinessException;
import com.example.miaosha.service.model.UserModel;

public interface UserService {
    //通过用户id获取用户对象
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;
    // phone：用户注册手机
    // password：用户加密后的密码
    UserModel validateLogin(String phone,String encryptPassword) throws BusinessException;
}