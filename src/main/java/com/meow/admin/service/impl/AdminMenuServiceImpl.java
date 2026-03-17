package com.meow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meow.admin.exception.ServiceException;
import com.meow.admin.mapper.AdminMenuMapper;
import com.meow.admin.mapper.AdminRoleMenuMapper;
import com.meow.admin.model.dto.AdminMenuDTO;
import com.meow.admin.model.entity.AdminMenu;
import com.meow.admin.model.entity.AdminRoleMenu;
import com.meow.admin.model.vo.AdminMenuVO;
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
 * 后台菜单服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMenuServiceImpl extends ServiceImpl<AdminMenuMapper, AdminMenu> implements com.meow.admin.service.AdminMenuService {
    
    private final AdminRoleMenuMapper adminRoleMenuMapper;
    
    @Override
    public List<AdminMenuVO> getMenuTree(String menuName, Boolean status) {
        LambdaQueryWrapper<AdminMenu> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(menuName)) {
            queryWrapper.like(AdminMenu::getMenuName, menuName);
        }
        if (status != null) {
            queryWrapper.eq(AdminMenu::getStatus, status);
        }
        queryWrapper.orderByAsc(AdminMenu::getSortOrder);
        
        List<AdminMenu> allMenus = this.list(queryWrapper);
        return buildMenuTree(allMenus, 0L);
    }
    
    @Override
    public AdminMenuVO getMenuById(Long id) {
        AdminMenu menu = this.getById(id);
        if (menu == null) {
            throw new ServiceException(ResultCode.DATA_NOT_FOUND.getCode(), "菜单不存在");
        }
        return convertToVO(menu);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminMenuVO createMenu(AdminMenuDTO dto) {
        // 检查菜单编码是否已存在
        LambdaQueryWrapper<AdminMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminMenu::getMenuCode, dto.getMenuCode());
        if (this.count(queryWrapper) > 0) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "菜单编码已存在");
        }
        
        // 如果有父菜单，检查父菜单是否存在
        if (dto.getParentId() != null && dto.getParentId() > 0) {
            AdminMenu parentMenu = this.getById(dto.getParentId());
            if (parentMenu == null) {
                throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "父菜单不存在");
            }
        }
        
        AdminMenu menu = new AdminMenu();
        BeanUtils.copyProperties(dto, menu);
        menu.setCreatedAt(LocalDateTime.now());
        menu.setUpdatedAt(LocalDateTime.now());
        
        if (!this.save(menu)) {
            throw new ServiceException(ResultCode.DATABASE_INSERT_FAILED);
        }
        
        return convertToVO(menu);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminMenuVO updateMenu(AdminMenuDTO dto) {
        if (dto.getId() == null) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "菜单ID不能为空");
        }
        
        AdminMenu menu = this.getById(dto.getId());
        if (menu == null) {
            throw new ServiceException(ResultCode.DATA_NOT_FOUND.getCode(), "菜单不存在");
        }
        
        // 检查菜单编码是否已被其他菜单使用
        LambdaQueryWrapper<AdminMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminMenu::getMenuCode, dto.getMenuCode());
        queryWrapper.ne(AdminMenu::getId, dto.getId());
        if (this.count(queryWrapper) > 0) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "菜单编码已存在");
        }
        
        // 不能将父菜单设置为自己或自己的子菜单
        if (dto.getParentId() != null && dto.getParentId().equals(dto.getId())) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "不能将父菜单设置为自己");
        }
        
        BeanUtils.copyProperties(dto, menu);
        menu.setUpdatedAt(LocalDateTime.now());
        
        if (!this.updateById(menu)) {
            throw new ServiceException(ResultCode.DATABASE_UPDATE_FAILED);
        }
        
        return convertToVO(menu);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteMenu(Long id) {
        AdminMenu menu = this.getById(id);
        if (menu == null) {
            throw new ServiceException(ResultCode.DATA_NOT_FOUND.getCode(), "菜单不存在");
        }
        
        // 检查是否有子菜单
        LambdaQueryWrapper<AdminMenu> childQuery = new LambdaQueryWrapper<>();
        childQuery.eq(AdminMenu::getParentId, id);
        long childCount = this.count(childQuery);
        if (childCount > 0) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "该菜单下有子菜单，无法删除");
        }
        
        // 删除角色菜单关联
        LambdaQueryWrapper<AdminRoleMenu> roleMenuQuery = new LambdaQueryWrapper<>();
        roleMenuQuery.eq(AdminRoleMenu::getMenuId, id);
        adminRoleMenuMapper.delete(roleMenuQuery);
        
        // 删除菜单
        return this.removeById(id);
    }
    
    @Override
    public List<AdminMenuVO> getMenuTreeByUserId(Long userId) {
        List<AdminMenu> menus = baseMapper.selectMenusByUserId(userId);
        return buildMenuTree(menus, 0L);
    }
    
    @Override
    public List<AdminMenuVO> getMenusByRoleId(Long roleId) {
        List<AdminMenu> menus = baseMapper.selectMenusByRoleId(roleId);
        return menus.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<AdminMenuVO> getAllMenus() {
        LambdaQueryWrapper<AdminMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminMenu::getStatus, true);
        queryWrapper.orderByAsc(AdminMenu::getSortOrder);
        
        List<AdminMenu> menus = this.list(queryWrapper);
        return menus.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }
    
    /**
     * 构建菜单树
     */
    private List<AdminMenuVO> buildMenuTree(List<AdminMenu> allMenus, Long parentId) {
        List<AdminMenuVO> tree = new ArrayList<>();
        
        for (AdminMenu menu : allMenus) {
            if (menu.getParentId().equals(parentId)) {
                AdminMenuVO vo = convertToVO(menu);
                
                // 递归查找子菜单
                List<AdminMenuVO> children = buildMenuTree(allMenus, menu.getId());
                if (!children.isEmpty()) {
                    vo.setChildren(children);
                }
                
                tree.add(vo);
            }
        }
        
        return tree;
    }
    
    /**
     * 转换为VO对象
     */
    private AdminMenuVO convertToVO(AdminMenu menu) {
        if (menu == null) {
            return null;
        }
        AdminMenuVO vo = new AdminMenuVO();
        BeanUtils.copyProperties(menu, vo);
        return vo;
    }
}

