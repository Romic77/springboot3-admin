package com.meow.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 后台管理员用户实体类
 */
@Data
@TableName("t_admin_user")
public class AdminUser {
    
    /**
     * 管理员ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 登录账号
     */
    private String username;
    
    /**
     * 加密后的密码
     */
    private String password;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 电子邮箱
     */
    private String email;
    
    /**
     * 手机号码
     */
    private String phone;
    
    /**
     * 头像URL
     */
    private String avatar;
    
    /**
     * 状态(0:禁用 1:启用)
     */
    private Boolean status;
    
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
    
    /**
     * 最后登录IP
     */
    private String lastLoginIp;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 记录创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 记录最后更新时间
     */
    private LocalDateTime updatedAt;
    
    /**
     * 是否删除(0:未删除 1:已删除)
     */
    @TableLogic
    private Boolean isDeleted;
}

