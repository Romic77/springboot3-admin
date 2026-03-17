package com.meow.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meow.admin.model.entity.AdminRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台角色Mapper接口
 */
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    
    /**
     * 根据用户ID查询角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<AdminRole> selectRolesByUserId(@Param("userId") Long userId);
}

