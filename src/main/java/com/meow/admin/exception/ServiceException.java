package com.meow.admin.exception;

import com.meow.admin.util.result.IErrorCode;
import lombok.Getter;

/**
 * 自定义业务异常
 */
@Getter
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 构造函数
     *
     * @param message 错误消息
     */
    public ServiceException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     */
    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     */
    public ServiceException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param message   错误消息
     */
    public ServiceException(IErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
        this.message = message;
    }

    public ServiceException(String message, Integer code) {
        this(code, message);
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
        this.code = null;
        this.message = message;
    }

    public ServiceException(String message, Throwable e, Integer code) {
        super(message, e);
        this.code = code;
        this.message = message;
    }
}