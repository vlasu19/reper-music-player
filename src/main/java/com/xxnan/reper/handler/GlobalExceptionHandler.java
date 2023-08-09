package com.xxnan.reper.handler;

import com.xxnan.reper.common.exception.BaseException;
import com.xxnan.reper.common.exception.DuplicateUsernameException;
import com.xxnan.reper.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public R exceptionHandler(BaseException ex){
        log.info("异常信息：{}",ex.getMessage());
        return R.error(ex.getMessage());
    }
    @ExceptionHandler
    public R exceptionHandler(DuplicateUsernameException ex){
        return R.warning(ex.getMessage());
    }
    @ExceptionHandler
    public R exceptionHandler(DuplicateKeyException ex){
        return R.fatal(ex.getMessage());
    }
}
