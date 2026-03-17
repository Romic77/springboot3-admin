package com.meow.admin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理员用户视图对象
 */
@Data
@Schema(description = "管理员用户视图对象")
public class AdminUserVO {
    
    @Schema(description = "用户ID")
    private Long id;
    
    @Schema(description = "登录账号")
    private String username;
    
    @Schema(description = "真实姓名")
    private String realName;
    
    @Schema(description = "电子邮箱")
    private String email;
    
    @Schema(description = "手机号码")
    private String phone;
    
    @Schema(description = "头像URL")
    private String avatar;
    
    @Schema(description = "状态(0:禁用 1:启用)")
    private Boolean status;
    
    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;
    
    @Schema(description = "最后登录IP")
    private String lastLoginIp;
    
    @Schema(description = "备注")
    private String remark;
    
    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;
    
    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
    
    @Schema(description = "角色列表")
    private List<AdminRoleVO> roles;
    
    @Schema(description = "角色名称列表（逗号分隔）")
    private String roleNames;
    
    @Schema(description = "登录token（仅登录时返回）")
    private String token;

    @Schema(description = "菜单列表")
    List<AdminMenuVO> menus;

}

