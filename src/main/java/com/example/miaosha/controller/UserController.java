package com.example.miaosha.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.example.miaosha.controller.vo.UserVO;
import com.example.miaosha.error.BusinessException;
import com.example.miaosha.error.EmBusinessError;
import com.example.miaosha.response.CommonReturnType;
import com.example.miaosha.service.UserService;
import com.example.miaosha.service.model.UserModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id")Integer id) throws BusinessException {
        // 调用service服务，获取对应id的用户对象，并且返回给前端
        UserModel userModel = userService.getUserById(id);
        if(userModel==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        // 将核心领域模型用户对象，转化为可供UI使用的VO
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    // 用户获取otp短信接口
    @RequestMapping(method={RequestMethod.POST}, value = "/getotp",consumes = {BaseController.CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "phone")String phone){
        // 按照一定的规则生成otp验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);
        // 将OTP验证码与对应用户的手机号关联，一般使用redis使用。
        // 目前还是http session的方式。
        httpServletRequest.getSession().setAttribute(phone, otpCode);
        // 将OTP验证码通过短信通道，发送给用户，省略
        LOGGER.info("phone = " +phone+"& otpCode = " +otpCode);
        System.out.println("phone = " +phone+"& otpCode = " +otpCode);
        return CommonReturnType.create(null);
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