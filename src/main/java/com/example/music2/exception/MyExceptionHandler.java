package com.example.music2.exception;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.music2.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Result<?> exceptionHandle(){
        return Result.error();
    }

    //捕获自定义异常
    @ExceptionHandler(value = CustomException.class)
    public Result<?> CustomerExceptionHandler(CustomException e) {
        log.error("错误原因为:" + e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldError = e.getBindingResult().getFieldErrors();
        String defaultMessage = fieldError.get(0).getDefaultMessage();
        return Result.error(defaultMessage);
    }
}