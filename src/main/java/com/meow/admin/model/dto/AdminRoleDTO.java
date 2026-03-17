package com.meow.admin.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 角色数据传输对象
 */
@Data
@Schema(description = "角色数据传输对象")
public class AdminRoleDTO {
    
    @Schema(description = "角色ID（更新时必填）")
    private Long id;
    
    @NotBlank(message = "角色名称不能为空")
    @Schema(description = "角色名称", required = true)
    private String roleName;
    
    @NotBlank(message = "角色编码不能为空")
    @Schema(description = "角色编码", required = true)
    private String roleCode;
    
    @Schema(description = "显示顺序")
    private Integer sortOrder;
    
    @NotNull(message = "状态不能为空")
    @Schema(description = "状态(0:禁用 1:启用)", required = true)
    private Boolean status;
    
    @Schema(description = "备注")
    private String remark;
    
    @Schema(description = "菜单ID列表")
    private List<Long> menuIds;
}

