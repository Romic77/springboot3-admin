package com.meow.admin.result;

import java.io.Serializable;

/**
 * 业务状态码
 */
public enum ResultCode implements IErrorCode, Serializable {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    VALIDATE_FAILED(404, "参数检验失败"),

    // 数据错误
    DATA_NOT_FOUND(10001, "数据不存在"),
    
    // 数据库操作错误
    DATABASE_INSERT_FAILED(11001, "数据库插入失败"),
    DATABASE_UPDATE_FAILED(11002, "数据库更新失败"),
    DATABASE_DELETE_FAILED(11003, "数据库删除失败"),
    
    // 用户相关错误
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_HAS_EXISTED(20002, "用户已存在"),
    USER_LOGIN_ERROR(20003, "账号或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20004, "账号已被禁用"),
    USER_NOT_LOGIN(20005, "用户未登录"),
    USER_NO_PERMISSION(20006, "用户无权限"),
    USER_LOGIN_EXPIRED(20007, "登录已过期，请重新登录"),

    FILE_UPLOAD_FAILED(30001, "文件上传失败"),;

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}