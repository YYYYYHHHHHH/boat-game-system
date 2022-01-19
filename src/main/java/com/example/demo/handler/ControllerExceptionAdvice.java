package com.example.demo.handler;

import com.example.demo.exception.UserException;
import com.example.demo.status.ResultVo;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ControllerExceptionAdvice {
    @ExceptionHandler(UserException.class)
    public ResultVo userExceptionHandler(UserException e) {
        String data = e.getMessage();

        return new ResultVo(e, data);
    }
}
