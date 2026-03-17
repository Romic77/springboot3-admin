package com.meow.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meow.admin.model.entity.AdminRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台角色菜单关联Mapper接口
 */
@Mapper
public interface AdminRoleMenuMapper extends BaseMapper<AdminRoleMenu> {
}

