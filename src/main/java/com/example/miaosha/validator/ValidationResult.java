package com.example.miaosha.validator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ValidationResult {
    private boolean hasErrors = false;
    private Map<String,String> errorMsgMap = new HashMap<>();

    public boolean isHasErrors() {
        return this.hasErrors;
    }

    public boolean getHasErrors() {
        return this.hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String,String> getErrorMsgMap() {
        return this.errorMsgMap;
    }

    public void setErrorMsgMap(Map<String,String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    // 实现通用的通过格式化字符串信息，获取错误结果的msg方法。
    public String getErrMsg(){
        return StringUtils.join(errorMsgMap.values().toArray(),",");
    }
}