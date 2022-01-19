package com.example.demo.exception;

import com.example.demo.status.StatusCode;

public class UserException extends Exception implements StatusCode {

    private int code;
    private String msg;
    private boolean success;

    public UserException(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.success = statusCode.getSuccess();
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
