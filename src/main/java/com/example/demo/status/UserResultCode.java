package com.example.demo.status;

public enum UserResultCode implements StatusCode {
    SUCCESS(200, "登录成功", true), LOGIN_FAILD(201, "用户不存在或密码错误", false);

    private int code;
    private String msg;
    private boolean success;

    UserResultCode(int code, String msg, boolean success) {
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
