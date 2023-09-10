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

    //自定义异常处理
    @ExceptionHandler(SSyxException.class)
    @ResponseBody
    public Result error(SSyxException exception) {
        return Result.build(null,exception.getCode(),exception.getMessage());
    }
}
