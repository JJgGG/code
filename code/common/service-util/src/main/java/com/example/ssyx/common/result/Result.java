package com.example.ssyx.common.result;

import lombok.Data;

@Data
public class Result<T> {
    //状态码
    private Integer code;
    //信息
    private String message;
    //数据
    private T data;

    //构造私有化
    private Result(){}

    //设置数据，返回对象的方法
    public static<T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        //创建result对象，并且设置值，再返回对象
        Result<T> result = new Result<>();
        //判断是否需要数据
        if(data != null){
            //设置数据到对象
            result.setData(data);
        }
        result.setCode(result.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        //返回对象
        return result;
    }
    //成功的方法
    public static<T> Result<T> ok(T data){
        Result<T> result = build(data,ResultCodeEnum.SUCCESS);
        return result;
    }
    //失败的方法
    public static<T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }
}
