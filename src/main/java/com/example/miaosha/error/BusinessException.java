package com.example.miaosha.error;

// 包装器业务异常类实现
public class BusinessException extends Exception implements CommonError{
    /**
     *
     */
    private static final long serialVersionUID = -2240848338476296163L;
    private CommonError commonError;
    
    // 直接接受EmBusinessError的传参，用于构造业务异常
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    // 接受自定义的errMsg的方式，构造业务异常
    public BusinessException(CommonError err, String errMsg){
        super();
        this.commonError = err;
        this.commonError.setErrMsg(errMsg);

    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return this.commonError.setErrMsg(errMsg);
    }
}