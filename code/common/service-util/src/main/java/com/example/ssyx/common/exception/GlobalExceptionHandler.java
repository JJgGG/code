package com.example.ssyx.common.exception;

import com.example.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//增强功能（AOP）
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//遇到Exception异常就返回
    @ResponseBody//返回数据通过json格式进行返回
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null);
    }

    @ExceptionHandler(SSyxException.class)
    @ResponseBody
    public Result error(SSyxException exception){
        exception.printStackTrace();
        return Result.fail(null);
    }
}
