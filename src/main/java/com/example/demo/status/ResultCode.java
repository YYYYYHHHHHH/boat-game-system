package com.example.demo.status;

public enum ResultCode implements StatusCode {
    SUCCESS(1000, "请求成功", true), FAILED(1001, "请求失败", false), VALIDATE_ERROR(1002, "参数校验失败", false),
    RESPONSE_PACK_ERROR(1003, "response返回包装失败", false);

    private int code;
    private String msg;
    private boolean success;

    ResultCode(int code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public boolean getSuccess() {
        return success;
    }
}
