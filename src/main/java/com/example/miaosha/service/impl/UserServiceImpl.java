package com.example.miaosha.service.impl;

import com.example.miaosha.dao.UserDOMapper;
import com.example.miaosha.dao.UserPasswordDOMapper;
import com.example.miaosha.dataobject.UserDO;
import com.example.miaosha.dataobject.UserPasswordDO;
import com.example.miaosha.service.UserService;
import com.example.miaosha.service.model.UserModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDO == null){
            return null;
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        return convertFromDataObject(userDO,userPasswordDO);
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if (userDO == null){
            return null;
        }  
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if(userPasswordDO != null){
            userModel.setEncryptPassword(userPasswordDO.getEncrptPasssword());
        }
        return userModel;
    }
}