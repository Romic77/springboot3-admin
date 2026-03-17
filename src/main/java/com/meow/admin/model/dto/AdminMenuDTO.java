package com.meow.admin.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 菜单数据传输对象
 */
@Data
@Schema(description = "菜单数据传输对象")
public class AdminMenuDTO {
    
    @Schema(description = "菜单ID（更新时必填）")
    private Long id;
    
    @NotNull(message = "父菜单ID不能为空")
    @Schema(description = "父菜单ID(0表示一级菜单)", required = true)
    private Long parentId;
    
    @NotBlank(message = "菜单名称不能为空")
    @Schema(description = "菜单名称", required = true)
    private String menuName;
    
    @NotBlank(message = "菜单编码不能为空")
    @Schema(description = "菜单编码", required = true)
    private String menuCode;
    
    @NotNull(message = "菜单类型不能为空")
    @Schema(description = "菜单类型(1:目录 2:菜单)", required = true)
    private Integer menuType;
    
    @Schema(description = "路由地址")
    private String path;
    
    @Schema(description = "组件路径")
    private String component;
    
    @Schema(description = "菜单图标")
    private String icon;
    
    @Schema(description = "显示顺序")
    private Integer sortOrder;
    
    @NotNull(message = "是否显示不能为空")
    @Schema(description = "是否显示(0:隐藏 1:显示)", required = true)
    private Boolean visible;
    
    @NotNull(message = "状态不能为空")
    @Schema(description = "状态(0:禁用 1:启用)", required = true)
    private Boolean status;
    
    @Schema(description = "备注")
    private String remark;
}

