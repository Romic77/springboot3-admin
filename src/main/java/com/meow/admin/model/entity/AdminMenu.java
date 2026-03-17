package com.meow.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 后台菜单实体类
 */
@Data
@TableName("t_admin_menu")
public class AdminMenu {
    
    /**
     * 菜单ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 父菜单ID(0表示一级菜单)
     */
    private Long parentId;
    
    /**
     * 菜单名称
     */
    private String menuName;
    
    /**
     * 菜单编码
     */
    private String menuCode;
    
    /**
     * 菜单类型(1:目录 2:菜单)
     */
    private Integer menuType;
    
    /**
     * 路由地址
     */
    private String path;
    
    /**
     * 组件路径
     */
    private String component;
    
    /**
     * 菜单图标
     */
    private String icon;
    
    /**
     * 显示顺序
     */
    private Integer sortOrder;
    
    /**
     * 是否显示(0:隐藏 1:显示)
     */
    private Boolean visible;
    
    /**
     * 状态(0:禁用 1:启用)
     */
    private Boolean status;
    
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

