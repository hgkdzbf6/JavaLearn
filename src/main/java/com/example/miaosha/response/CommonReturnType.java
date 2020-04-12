package com.example.miaosha.response;

public class CommonReturnType{
    // 表明对应请求的返回处理结果"success"或者"fail"
    private String status;
    // 如果status=success，则data内返回前端需要的json数据。
    // 如果status=fail，则data内使用通用的错误码格式。
    private Object data;

    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }
    public static CommonReturnType create(Object data, String success){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(success);
        type.setData(data);
        return type;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}