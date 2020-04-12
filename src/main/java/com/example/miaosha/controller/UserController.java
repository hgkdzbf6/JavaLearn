package com.example.miaosha.controller;

import com.example.miaosha.controller.vo.UserVO;
import com.example.miaosha.response.CommonReturnType;
import com.example.miaosha.service.UserService;
import com.example.miaosha.service.model.UserModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id")Integer id){
        // 调用service服务，获取对应id的用户对象，并且返回给前端
        UserModel userModel = userService.getUserById(id);
        // 将核心领域模型用户对象，转化为可供UI使用的VO
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}