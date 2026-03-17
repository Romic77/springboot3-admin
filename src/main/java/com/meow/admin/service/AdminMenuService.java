package com.meow.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meow.admin.model.dto.AdminMenuDTO;
import com.meow.admin.model.entity.AdminMenu;
import com.meow.admin.model.vo.AdminMenuVO;

import java.util.List;

/**
 * 后台菜单服务接口
 */
public interface AdminMenuService extends IService<AdminMenu> {
    
    /**
     * 获取菜单树形列表
     *
     * @param menuName 菜单名称（可选）
     * @param status   状态（可选）
     * @return 菜单树形列表
     */
    List<AdminMenuVO> getMenuTree(String menuName, Boolean status);
    
    /**
     * 根据ID获取菜单详情
     *
     * @param id 菜单ID
     * @return 菜单详情
     */
    AdminMenuVO getMenuById(Long id);
    
    /**
     * 创建菜单
     *
     * @param dto 菜单数据
     * @return 创建的菜单
     */
    AdminMenuVO createMenu(AdminMenuDTO dto);
    
    /**
     * 更新菜单
     *
     * @param dto 菜单数据
     * @return 更新后的菜单
     */
    AdminMenuVO updateMenu(AdminMenuDTO dto);
    
    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return 是否成功
     */
    Boolean deleteMenu(Long id);
    
    /**
     * 根据用户ID获取菜单树
     *
     * @param userId 用户ID
     * @return 菜单树
     */
    List<AdminMenuVO> getMenuTreeByUserId(Long userId);
    
    /**
     * 根据角色ID获取菜单列表
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<AdminMenuVO> getMenusByRoleId(Long roleId);
    
    /**
     * 获取所有菜单列表（不分页，用于下拉选择）
     *
     * @return 菜单列表
     */
    List<AdminMenuVO> getAllMenus();
}

