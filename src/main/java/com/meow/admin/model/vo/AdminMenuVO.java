package com.meow.admin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单视图对象
 */
@Data
@Schema(description = "菜单视图对象")
public class AdminMenuVO {
    
    @Schema(description = "菜单ID")
    private Long id;
    
    @Schema(description = "父菜单ID(0表示一级菜单)")
    private Long parentId;
    
    @Schema(description = "菜单名称")
    private String menuName;
    
    @Schema(description = "菜单编码")
    private String menuCode;
    
    @Schema(description = "菜单类型(1:目录 2:菜单)")
    private Integer menuType;
    
    @Schema(description = "路由地址")
    private String path;
    
    @Schema(description = "组件路径")
    private String component;
    
    @Schema(description = "菜单图标")
    private String icon;
    
    @Schema(description = "显示顺序")
    private Integer sortOrder;
    
    @Schema(description = "是否显示(0:隐藏 1:显示)")
    private Boolean visible;
    
    @Schema(description = "状态(0:禁用 1:启用)")
    private Boolean status;
    
    @Schema(description = "备注")
    private String remark;
    
    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;
    
    @Schema(description = "记录最后更新时间")
    private LocalDateTime updatedAt;
    
    @Schema(description = "子菜单列表")
    private List<AdminMenuVO> children;
}

