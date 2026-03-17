package com.meow.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meow.admin.model.entity.AdminUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户角色关联Mapper接口
 */
@Mapper
public interface AdminUserRoleMapper extends BaseMapper<AdminUserRole> {
}

