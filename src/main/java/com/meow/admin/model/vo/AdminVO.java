package com.meow.admin.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理员VO
 */
@Data
public class AdminVO {
    
    /**
     * 管理员ID
     */
    private Long id;
    
    /**
     * 登录账号
     */
    private String username;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 电子邮箱
     */
    private String email;
    
    /**
     * 状态(0:禁用 1:启用)
     */
    private Boolean status;
    
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
    
    /**
     * 记录创建时间
     */
    private LocalDateTime createdAt;

    /**
     *  token
     */
    private String token;
} 