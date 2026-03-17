package com.meow.admin.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.meow.admin.util.result.Result;
import com.meow.admin.util.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result<Void> handleServiceException(ServiceException e) {
        log.error("业务异常：{}", e.getMessage(), e);
        return Result.failed(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数验证异常
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public Result<Void> handleValidException(Exception e) {
        BindingResult bindingResult;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else {
            bindingResult = ((BindException) e).getBindingResult();
        }
        
        StringBuilder sb = new StringBuilder("参数校验失败：");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(", ");
        }
        
        String message = sb.toString();
        if (message.endsWith(", ")) {
            message = message.substring(0, message.length() - 2);
        }
        
        log.warn(message);
        return Result.failed(ResultCode.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 处理SaToken登录认证异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<Void> handleNotLoginException(NotLoginException e) {
        log.warn("用户未登录：{}", e.getMessage());
        return Result.failed(ResultCode.UNAUTHORIZED);
    }

    /**
     * 处理SaToken权限异常
     */
    @ExceptionHandler({NotPermissionException.class, NotRoleException.class})
    public Result<Void> handleNotPermissionException(Exception e) {
        log.warn("权限不足：{}", e.getMessage());
        return Result.failed(ResultCode.FORBIDDEN);
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常：{}", e.getMessage(), e);
        return Result.failed(ResultCode.FAILED);
    }
} 