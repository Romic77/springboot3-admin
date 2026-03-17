package com.meow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meow.admin.model.dto.AdminRoleDTO;
import com.meow.admin.model.entity.AdminRole;
import com.meow.admin.model.vo.AdminRoleVO;

import java.util.List;

/**
 * 后台角色服务接口
 */
public interface AdminRoleService extends IService<AdminRole> {
    
    /**
     * 分页查询角色列表
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param roleName 角色名称（可选）
     * @param status   状态（可选）
     * @return 角色分页列表
     */
    IPage<AdminRoleVO> pageRoles(Integer pageNum, Integer pageSize, String roleName, Boolean status);
    
    /**
     * 根据ID获取角色详情
     *
     * @param id 角色ID
     * @return 角色详情
     */
    AdminRoleVO getRoleById(Long id);
    
    /**
     * 创建角色
     *
     * @param dto 角色数据
     * @return 创建的角色
     */
    AdminRoleVO createRole(AdminRoleDTO dto);
    
    /**
     * 更新角色
     *
     * @param dto 角色数据
     * @return 更新后的角色
     */
    AdminRoleVO updateRole(AdminRoleDTO dto);
    
    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return 是否成功
     */
    Boolean deleteRole(Long id);
    
    /**
     * 为角色分配菜单权限
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID列表
     * @return 是否成功
     */
    Boolean assignMenus(Long roleId, List<Long> menuIds);
    
    /**
     * 获取角色的菜单ID列表
     *
     * @param roleId 角色ID
     * @return 菜单ID列表
     */
    List<Long> getRoleMenuIds(Long roleId);
    
    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<AdminRoleVO> getRolesByUserId(Long userId);
    
    /**
     * 获取所有角色列表（不分页）
     *
     * @return 角色列表
     */
    List<AdminRoleVO> getAllRoles();
}

