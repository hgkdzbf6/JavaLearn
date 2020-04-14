package com.example.miaosha.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;

import com.example.miaosha.controller.vo.UserVO;
import com.example.miaosha.error.BusinessException;
import com.example.miaosha.error.EmBusinessError;
import com.example.miaosha.response.CommonReturnType;
import com.example.miaosha.service.UserService;
import com.example.miaosha.service.model.UserModel;

import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/getotp")
    public String greeting(){
        return "getotp";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        // 调用service服务，获取对应id的用户对象，并且返回给前端
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        // 将核心领域模型用户对象，转化为可供UI使用的VO
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    // 用户获取otp短信接口
    @RequestMapping(method = { RequestMethod.POST }, value = "/getotp", consumes = {
            BaseController.CONTENT_TYPE_FORMED })
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "phone") String phone) {
        // 按照一定的规则生成otp验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);
        // 将OTP验证码与对应用户的手机号关联，一般使用redis使用。
        // 目前还是http session的方式。
        httpServletRequest.getSession().setAttribute(phone, otpCode);
        // 将OTP验证码通过短信通道，发送给用户，省略
        LOGGER.info("phone = " + phone + "& otpCode = " + otpCode);
        System.out.println("phone = " + phone + "& otpCode = " + otpCode);
        return CommonReturnType.create(null);
    }

    // 用户注册接口
    @RequestMapping(method = { RequestMethod.POST }, value = "/register", consumes = {
            BaseController.CONTENT_TYPE_FORMED })
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "phone") String phone,
            @RequestParam(name = "otpCode") String otpCode, @RequestParam(name = "name") String name,
            @RequestParam(name = "gender") Byte gender, @RequestParam(name = "age") Integer age,
            @RequestParam(name = "password") String password) throws BusinessException, NoSuchAlgorithmException,
            UnsupportedEncodingException {

        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(phone);
        if (!com.alibaba.druid.util.StringUtils.equals(otpCode, inSessionOtpCode)) {
            // 为啥要用类库里的equals呢？是因为druid内部已经实现了判空处理。
            // if (!inSessionOtpCode.equals(otpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不符合");
        }
        // 进入用户的处理流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setPhone(phone);
        userModel.setRegisterMode("byPhone");
        userModel.setEncryptPassword(EncodeByMD5(password));

        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    public String EncodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定一个计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
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