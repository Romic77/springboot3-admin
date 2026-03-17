package com.meow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meow.admin.model.dto.AdminLoginDTO;
import com.meow.admin.model.dto.AdminRegisterDTO;
import com.meow.admin.model.dto.AdminUpdatePasswordDTO;
import com.meow.admin.model.dto.AdminUserDTO;
import com.meow.admin.model.entity.AdminUser;
import com.meow.admin.model.vo.AdminUserVO;

import java.util.List;

/**
 * 管理员用户服务接口
 */
public interface AdminUserService extends IService<AdminUser> {
    
    /**
     * 管理员登录
     * @param loginDTO 登录参数
     * @return 登录成功返回token等信息（包含角色和菜单）
     */
    AdminUserVO login(AdminLoginDTO loginDTO);
    
    /**
     * 管理员注册
     * @param registerDTO 注册参数
     * @return 注册的管理员信息
     */
    AdminUserVO register(AdminRegisterDTO registerDTO);
    
    /**
     * 根据ID获取管理员信息
     * @param id 管理员ID
     * @return 管理员信息（包含角色）
     */
    AdminUserVO getAdminUserById(Long id);
    
    /**
     * 根据用户名获取管理员信息
     * @param username 用户名
     * @return 管理员信息
     */
    AdminUser getAdminUserByUsername(String username);
    
    /**
     * 分页查询管理员列表
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param username 用户名（可选）
     * @param status 状态（可选）
     * @return 管理员分页列表
     */
    IPage<AdminUserVO> pageAdminUsers(Integer pageNum, Integer pageSize, String username, Boolean status);
    
    /**
     * 修改密码
     * @param dto 修改密码参数
     * @param adminId 管理员ID
     */
    void updatePassword(AdminUpdatePasswordDTO dto, Long adminId);
    
    /**
     * 创建管理员用户
     * @param dto 管理员用户数据
     * @return 创建的管理员用户
     */
    AdminUserVO createAdminUser(AdminUserDTO dto);
    
    /**
     * 更新管理员用户
     * @param dto 管理员用户数据
     * @return 更新后的管理员用户
     */
    AdminUserVO updateAdminUser(AdminUserDTO dto);
    
    /**
     * 删除管理员用户
     * @param id 管理员ID
     * @return 是否成功
     */
    Boolean deleteAdminUser(Long id);
    
    /**
     * 为用户分配角色
     * @param userId 用户ID
     * @param roleIds 角色ID列表
     * @return 是否成功
     */
    Boolean assignRoles(Long userId, List<Long> roleIds);
    
    /**
     * 获取用户的角色ID列表
     * @param userId 用户ID
     * @return 角色ID列表
     */
    List<Long> getUserRoleIds(Long userId);
    
    /**
     * 获取当前登录用户信息
     * @return 用户信息（包含角色和菜单）
     */
    AdminUserVO getCurrentUserInfo();
}

