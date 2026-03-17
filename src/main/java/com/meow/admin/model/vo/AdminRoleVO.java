package com.meow.admin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色视图对象
 */
@Data
@Schema(description = "角色视图对象")
public class AdminRoleVO {
    
    @Schema(description = "角色ID")
    private Long id;
    
    @Schema(description = "角色名称")
    private String roleName;
    
    @Schema(description = "角色编码")
    private String roleCode;
    
    @Schema(description = "显示顺序")
    private Integer sortOrder;
    
    @Schema(description = "状态(0:禁用 1:启用)")
    private Boolean status;
    
    @Schema(description = "备注")
    private String remark;
    
    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;
    
    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
    
    @Schema(description = "菜单列表")
    private List<AdminMenuVO> menus;
    
    @Schema(description = "菜单ID列表")
    private List<Long> menuIds;
}

