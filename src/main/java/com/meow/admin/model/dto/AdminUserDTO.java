package com.meow.admin.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 管理员用户数据传输对象
 */
@Data
@Schema(description = "管理员用户数据传输对象")
public class AdminUserDTO {
    
    @Schema(description = "用户ID（更新时必填）")
    private Long id;
    
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "登录账号", required = true)
    private String username;
    
    @Schema(description = "密码（新增时必填，更新时选填）")
    private String password;
    
    @Schema(description = "真实姓名")
    private String realName;
    
    @Email(message = "邮箱格式不正确")
    @Schema(description = "电子邮箱")
    private String email;
    
    @Schema(description = "手机号码")
    private String phone;
    
    @Schema(description = "头像URL")
    private String avatar;
    
    @NotNull(message = "状态不能为空")
    @Schema(description = "状态(0:禁用 1:启用)", required = true)
    private Boolean status;
    
    @Schema(description = "备注")
    private String remark;
    
    @Schema(description = "角色ID列表")
    private List<Long> roleIds;
}

