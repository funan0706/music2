package com.example.music2.exception;

import lombok.Getter;

/**
 * 处理运行时异常
 * 防止例如插入名字字符串过长，引起的问题
 * 导致代码结构错误
* */
@Getter
public class CustomException  extends RuntimeException{
    private Integer code;
    public CustomException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }
    public CustomException(String msg) {
        super(msg);
    }
}