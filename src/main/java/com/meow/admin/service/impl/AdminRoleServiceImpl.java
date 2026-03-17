package com.meow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meow.admin.exception.ServiceException;
import com.meow.admin.mapper.AdminMenuMapper;
import com.meow.admin.mapper.AdminRoleMapper;
import com.meow.admin.mapper.AdminRoleMenuMapper;
import com.meow.admin.mapper.AdminUserRoleMapper;
import com.meow.admin.model.dto.AdminRoleDTO;
import com.meow.admin.model.entity.AdminRole;
import com.meow.admin.model.entity.AdminRoleMenu;
import com.meow.admin.model.entity.AdminUserRole;
import com.meow.admin.model.vo.AdminRoleVO;
import com.meow.admin.util.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台角色服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements com.meow.admin.service.AdminRoleService {
    
    private final AdminRoleMenuMapper adminRoleMenuMapper;
    private final AdminUserRoleMapper adminUserRoleMapper;
    private final AdminMenuMapper adminMenuMapper;
    
    @Override
    public IPage<AdminRoleVO> pageRoles(Integer pageNum, Integer pageSize, String roleName, Boolean status) {
        Page<AdminRole> page = new Page<>(pageNum, pageSize);
        
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(roleName)) {
            queryWrapper.like(AdminRole::getRoleName, roleName);
        }
        if (status != null) {
            queryWrapper.eq(AdminRole::getStatus, status);
        }
        queryWrapper.orderByAsc(AdminRole::getSortOrder);
        
        IPage<AdminRole> rolePage = this.page(page, queryWrapper);
        return rolePage.convert(this::convertToVO);
    }
    
    @Override
    public AdminRoleVO getRoleById(Long id) {
        AdminRole role = this.getById(id);
        if (role == null) {
            throw new ServiceException(ResultCode.DATA_NOT_FOUND.getCode(), "角色不存在");
        }
        
        AdminRoleVO vo = convertToVO(role);
        
        // 查询角色的菜单ID列表
        List<Long> menuIds = getRoleMenuIds(id);
        vo.setMenuIds(menuIds);
        
        return vo;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminRoleVO createRole(AdminRoleDTO dto) {
        // 检查角色编码是否已存在
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminRole::getRoleCode, dto.getRoleCode());
        if (this.count(queryWrapper) > 0) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "角色编码已存在");
        }
        
        AdminRole role = new AdminRole();
        BeanUtils.copyProperties(dto, role);
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        
        if (!this.save(role)) {
            throw new ServiceException(ResultCode.DATABASE_INSERT_FAILED);
        }
        
        // 分配菜单权限
        if (dto.getMenuIds() != null && !dto.getMenuIds().isEmpty()) {
            assignMenus(role.getId(), dto.getMenuIds());
        }
        
        return getRoleById(role.getId());
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminRoleVO updateRole(AdminRoleDTO dto) {
        if (dto.getId() == null) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "角色ID不能为空");
        }
        
        AdminRole role = this.getById(dto.getId());
        if (role == null) {
            throw new ServiceException(ResultCode.DATA_NOT_FOUND.getCode(), "角色不存在");
        }
        
        // 检查角色编码是否已被其他角色使用
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminRole::getRoleCode, dto.getRoleCode());
        queryWrapper.ne(AdminRole::getId, dto.getId());
        if (this.count(queryWrapper) > 0) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "角色编码已存在");
        }
        
        BeanUtils.copyProperties(dto, role);
        role.setUpdatedAt(LocalDateTime.now());
        
        if (!this.updateById(role)) {
            throw new ServiceException(ResultCode.DATABASE_UPDATE_FAILED);
        }
        
        // 更新菜单权限
        if (dto.getMenuIds() != null) {
            assignMenus(role.getId(), dto.getMenuIds());
        }
        
        return getRoleById(role.getId());
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRole(Long id) {
        AdminRole role = this.getById(id);
        if (role == null) {
            throw new ServiceException(ResultCode.DATA_NOT_FOUND.getCode(), "角色不存在");
        }
        
        // 检查是否有用户使用该角色
        LambdaQueryWrapper<AdminUserRole> userRoleQuery = new LambdaQueryWrapper<>();
        userRoleQuery.eq(AdminUserRole::getRoleId, id);
        long userCount = adminUserRoleMapper.selectCount(userRoleQuery);
        if (userCount > 0) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "该角色已分配给用户，无法删除");
        }
        
        // 删除角色菜单关联
        LambdaQueryWrapper<AdminRoleMenu> roleMenuQuery = new LambdaQueryWrapper<>();
        roleMenuQuery.eq(AdminRoleMenu::getRoleId, id);
        adminRoleMenuMapper.delete(roleMenuQuery);
        
        // 删除角色
        return this.removeById(id);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignMenus(Long roleId, List<Long> menuIds) {
        // 删除原有的角色菜单关联
        LambdaQueryWrapper<AdminRoleMenu> deleteQuery = new LambdaQueryWrapper<>();
        deleteQuery.eq(AdminRoleMenu::getRoleId, roleId);
        adminRoleMenuMapper.delete(deleteQuery);
        
        // 添加新的角色菜单关联
        if (menuIds != null && !menuIds.isEmpty()) {
            List<AdminRoleMenu> roleMenus = new ArrayList<>();
            for (Long menuId : menuIds) {
                AdminRoleMenu roleMenu = new AdminRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenu.setCreatedAt(LocalDateTime.now());
                roleMenus.add(roleMenu);
            }
            
            // 批量插入
            for (AdminRoleMenu roleMenu : roleMenus) {
                adminRoleMenuMapper.insert(roleMenu);
            }
        }
        
        return true;
    }
    
    @Override
    public List<Long> getRoleMenuIds(Long roleId) {
        LambdaQueryWrapper<AdminRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminRoleMenu::getRoleId, roleId);
        List<AdminRoleMenu> roleMenus = adminRoleMenuMapper.selectList(queryWrapper);
        return roleMenus.stream()
                .map(AdminRoleMenu::getMenuId)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<AdminRoleVO> getRolesByUserId(Long userId) {
        List<AdminRole> roles = baseMapper.selectRolesByUserId(userId);
        return roles.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<AdminRoleVO> getAllRoles() {
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminRole::getStatus, true);
        queryWrapper.orderByAsc(AdminRole::getSortOrder);
        
        List<AdminRole> roles = this.list(queryWrapper);
        return roles.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }
    
    /**
     * 转换为VO对象
     */
    private AdminRoleVO convertToVO(AdminRole role) {
        if (role == null) {
            return null;
        }
        AdminRoleVO vo = new AdminRoleVO();
        BeanUtils.copyProperties(role, vo);
        return vo;
    }
}

