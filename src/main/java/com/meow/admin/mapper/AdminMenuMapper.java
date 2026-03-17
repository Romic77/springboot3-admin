package com.meow.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meow.admin.model.entity.AdminMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台菜单Mapper接口
 */
@Mapper
public interface AdminMenuMapper extends BaseMapper<AdminMenu> {
    
    /**
     * 根据用户ID查询菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<AdminMenu> selectMenusByUserId(@Param("userId") Long userId);
    
    /**
     * 根据角色ID查询菜单列表
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<AdminMenu> selectMenusByRoleId(@Param("roleId") Long roleId);
}

