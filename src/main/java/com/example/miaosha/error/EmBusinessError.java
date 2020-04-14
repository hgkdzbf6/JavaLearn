package com.example.miaosha.error;

public enum EmBusinessError implements CommonError{
    // 通用错误类型：00001
    UNKNOWN_ERROR(80000,"未知错误"),
    PARAMETER_VALIDATION_ERROR(80001,"参数不合法"),
    // 10000开头，为用户信息相关错误的定义
    USER_NOT_EXIST(10001,"用户不存在"),
    USER_LOGIN_FAIL(10002,"用户手机号或密码不正确")
    ;
    
    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

	@Override
	public int getErrCode() {
		return errCode;
	}

	@Override
	public String getErrMsg() {
		return errMsg;
	}

	@Override
	public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
	}

}