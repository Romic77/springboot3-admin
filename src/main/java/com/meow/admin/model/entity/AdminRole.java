package com.meow.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 后台角色实体类
 */
@Data
@TableName("t_admin_role")
public class AdminRole {
    
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 角色编码
     */
    private String roleCode;
    
    /**
     * 显示顺序
     */
    private Integer sortOrder;
    
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

