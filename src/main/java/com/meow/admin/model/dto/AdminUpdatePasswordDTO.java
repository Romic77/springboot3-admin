package com.meow.admin.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 管理员修改密码DTO
 */
@Data
public class AdminUpdatePasswordDTO {
    
    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    
    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
    
    /**
     * 确认新密码
     */
    @NotBlank(message = "确认新密码不能为空")
    private String confirmPassword;
} 