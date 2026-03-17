package com.meow.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meow.admin.exception.ServiceException;
import com.meow.admin.mapper.AdminUserMapper;
import com.meow.admin.mapper.AdminUserRoleMapper;
import com.meow.admin.model.dto.AdminLoginDTO;
import com.meow.admin.model.dto.AdminRegisterDTO;
import com.meow.admin.model.dto.AdminUpdatePasswordDTO;
import com.meow.admin.model.dto.AdminUserDTO;
import com.meow.admin.model.entity.AdminUser;
import com.meow.admin.model.entity.AdminUserRole;
import com.meow.admin.model.vo.AdminMenuVO;
import com.meow.admin.model.vo.AdminRoleVO;
import com.meow.admin.model.vo.AdminUserVO;
import com.meow.admin.service.AdminMenuService;
import com.meow.admin.service.AdminRoleService;
import com.meow.admin.service.AdminUserService;
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
 * 管理员用户服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    private final AdminUserRoleMapper adminUserRoleMapper;
    private final AdminRoleService adminRoleService;
    private final AdminMenuService adminMenuService;

    @Override
    public AdminUserVO login(AdminLoginDTO loginDTO) {
        // 根据用户名查询管理员
        AdminUser adminUser = getAdminUserByUsername(loginDTO.getUsername());
        if (adminUser == null) {
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }

        // 检查账户状态
        if (!adminUser.getStatus()) {
            throw new ServiceException(ResultCode.USER_ACCOUNT_FORBIDDEN);
        }

        // 验证密码
        if (!BCrypt.checkpw(loginDTO.getPassword(), adminUser.getPassword())) {
            throw new ServiceException(ResultCode.USER_LOGIN_ERROR);
        }

        // 更新最后登录时间和IP
        adminUser.setLastLoginTime(LocalDateTime.now());
        // adminUser.setLastLoginIp(request.getRemoteAddr());
        this.updateById(adminUser);

        // 登录，返回token
        StpUtil.login(adminUser.getId());

        // 构建返回VO
        AdminUserVO adminUserVO = convertToVO(adminUser);
        adminUserVO.setToken(StpUtil.getTokenValue());

        // 查询用户角色
        List<AdminRoleVO> roles = adminRoleService.getRolesByUserId(adminUser.getId());
        adminUserVO.setRoles(roles);

        // 设置角色名称列表
        if (roles != null && !roles.isEmpty()) {
            String roleNames = roles.stream()
                    .map(AdminRoleVO::getRoleName)
                    .collect(Collectors.joining(", "));
            adminUserVO.setRoleNames(roleNames);
        }

        List<AdminMenuVO> menus = adminMenuService.getMenuTreeByUserId(adminUser.getId());
        adminUserVO.setMenus(menus);

        return adminUserVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminUserVO register(AdminRegisterDTO registerDTO) {
        // 检查用户名是否已存在
        if (isUsernameExist(registerDTO.getUsername())) {
            throw new ServiceException(ResultCode.USER_HAS_EXISTED);
        }

        // 创建管理员实体并设置属性
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(registerDTO, adminUser);

        // 密码加密
        adminUser.setPassword(BCrypt.hashpw(registerDTO.getPassword()));

        // 设置默认值
        adminUser.setStatus(true);
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setUpdatedAt(LocalDateTime.now());

        // 保存到数据库
        if (!save(adminUser)) {
            throw new ServiceException(ResultCode.DATABASE_INSERT_FAILED);
        }

        // 转换为VO并返回
        return convertToVO(adminUser);
    }

    @Override
    public AdminUserVO getAdminUserById(Long id) {
        AdminUser adminUser = getById(id);
        if (adminUser == null) {
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }

        AdminUserVO vo = convertToVO(adminUser);

        // 查询用户角色
        List<AdminRoleVO> roles = adminRoleService.getRolesByUserId(id);
        vo.setRoles(roles);

        // 设置角色名称列表
        if (roles != null && !roles.isEmpty()) {
            String roleNames = roles.stream()
                    .map(AdminRoleVO::getRoleName)
                    .collect(Collectors.joining(", "));
            vo.setRoleNames(roleNames);
        }

        return vo;
    }

    @Override
    public AdminUser getAdminUserByUsername(String username) {
        return getOne(new LambdaQueryWrapper<AdminUser>()
                .eq(AdminUser::getUsername, username));
    }

    @Override
    public IPage<AdminUserVO> pageAdminUsers(Integer pageNum, Integer pageSize, String username, Boolean status) {
        // 构建查询条件
        LambdaQueryWrapper<AdminUser> queryWrapper = new LambdaQueryWrapper<>();

        // 添加用户名查询条件
        if (StringUtils.hasText(username)) {
            queryWrapper.like(AdminUser::getUsername, username);
        }

        // 添加状态查询条件
        if (status != null) {
            queryWrapper.eq(AdminUser::getStatus, status);
        }

        // 按创建时间降序排序
        queryWrapper.orderByDesc(AdminUser::getCreatedAt);

        // 执行分页查询
        Page<AdminUser> page = new Page<>(pageNum, pageSize);
        IPage<AdminUser> adminUserPage = page(page, queryWrapper);

        // 转换为VO
        return adminUserPage.convert(adminUser -> {
            AdminUserVO vo = convertToVO(adminUser);

            // 查询用户角色
            List<AdminRoleVO> roles = adminRoleService.getRolesByUserId(adminUser.getId());
            vo.setRoles(roles);

            // 设置角色名称列表
            if (roles != null && !roles.isEmpty()) {
                String roleNames = roles.stream()
                        .map(AdminRoleVO::getRoleName)
                        .collect(Collectors.joining(", "));
                vo.setRoleNames(roleNames);
            }

            return vo;
        });
    }

    @Override
    public void updatePassword(AdminUpdatePasswordDTO dto, Long adminId) {
        // 检查管理员是否存在
        AdminUser adminUser = getById(adminId);
        if (adminUser == null) {
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }

        // 验证旧密码是否正确
        if (!BCrypt.checkpw(dto.getOldPassword(), adminUser.getPassword())) {
            throw new ServiceException(ResultCode.USER_LOGIN_ERROR, "旧密码不正确");
        }

        // 验证新密码和确认密码是否一致
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED, "新密码和确认密码不一致");
        }

        // 加密新密码
        adminUser.setPassword(BCrypt.hashpw(dto.getNewPassword()));

        // 更新更新时间
        adminUser.setUpdatedAt(LocalDateTime.now());

        // 保存到数据库
        if (!updateById(adminUser)) {
            throw new ServiceException(ResultCode.DATABASE_UPDATE_FAILED);
        }

        log.info("管理员 {} 修改密码成功", adminUser.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminUserVO createAdminUser(AdminUserDTO dto) {
        // 检查用户名是否已存在
        if (isUsernameExist(dto.getUsername())) {
            throw new ServiceException(ResultCode.USER_HAS_EXISTED);
        }

        // 检查密码是否为空
        if (!StringUtils.hasText(dto.getPassword())) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "密码不能为空");
        }

        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(dto, adminUser);

        // 密码加密
        adminUser.setPassword(BCrypt.hashpw(dto.getPassword()));
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setUpdatedAt(LocalDateTime.now());

        if (!this.save(adminUser)) {
            throw new ServiceException(ResultCode.DATABASE_INSERT_FAILED);
        }

        // 分配角色
        if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
            assignRoles(adminUser.getId(), dto.getRoleIds());
        }

        return getAdminUserById(adminUser.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminUserVO updateAdminUser(AdminUserDTO dto) {
        if (dto.getId() == null) {
            throw new ServiceException(ResultCode.VALIDATE_FAILED.getCode(), "用户ID不能为空");
        }

        AdminUser adminUser = this.getById(dto.getId());
        if (adminUser == null) {
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }

        // 检查用户名是否已被其他用户使用
        LambdaQueryWrapper<AdminUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminUser::getUsername, dto.getUsername());
        queryWrapper.ne(AdminUser::getId, dto.getId());
        if (this.count(queryWrapper) > 0) {
            throw new ServiceException(ResultCode.USER_HAS_EXISTED);
        }

        // 复制属性（排除密码）
        String oldPassword = adminUser.getPassword();
        BeanUtils.copyProperties(dto, adminUser);

        // 如果提供了新密码，则更新密码
        if (StringUtils.hasText(dto.getPassword())) {
            adminUser.setPassword(BCrypt.hashpw(dto.getPassword()));
        } else {
            adminUser.setPassword(oldPassword);
        }

        adminUser.setUpdatedAt(LocalDateTime.now());

        if (!this.updateById(adminUser)) {
            throw new ServiceException(ResultCode.DATABASE_UPDATE_FAILED);
        }

        // 更新角色
        if (dto.getRoleIds() != null) {
            assignRoles(adminUser.getId(), dto.getRoleIds());
        }

        return getAdminUserById(adminUser.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAdminUser(Long id) {
        AdminUser adminUser = this.getById(id);
        if (adminUser == null) {
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }

        // 删除用户角色关联
        LambdaQueryWrapper<AdminUserRole> userRoleQuery = new LambdaQueryWrapper<>();
        userRoleQuery.eq(AdminUserRole::getUserId, id);
        adminUserRoleMapper.delete(userRoleQuery);

        // 删除用户（软删除）
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignRoles(Long userId, List<Long> roleIds) {
        // 删除原有的用户角色关联
        LambdaQueryWrapper<AdminUserRole> deleteQuery = new LambdaQueryWrapper<>();
        deleteQuery.eq(AdminUserRole::getUserId, userId);
        adminUserRoleMapper.delete(deleteQuery);

        // 添加新的用户角色关联
        if (roleIds != null && !roleIds.isEmpty()) {
            List<AdminUserRole> userRoles = new ArrayList<>();
            for (Long roleId : roleIds) {
                AdminUserRole userRole = new AdminUserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRole.setCreatedAt(LocalDateTime.now());
                userRoles.add(userRole);
            }

            // 批量插入
            for (AdminUserRole userRole : userRoles) {
                adminUserRoleMapper.insert(userRole);
            }
        }

        return true;
    }

    @Override
    public List<Long> getUserRoleIds(Long userId) {
        LambdaQueryWrapper<AdminUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminUserRole::getUserId, userId);
        List<AdminUserRole> userRoles = adminUserRoleMapper.selectList(queryWrapper);
        return userRoles.stream()
                .map(AdminUserRole::getRoleId)
                .collect(Collectors.toList());
    }

    @Override
    public AdminUserVO getCurrentUserInfo() {
        Long userId = StpUtil.getLoginIdAsLong();
        AdminUserVO userVO = getAdminUserById(userId);

        // 查询用户的菜单权限
        List<AdminMenuVO> menus = adminMenuService.getMenuTreeByUserId(userId);
        userVO.setMenus(menus);
        return userVO;
    }

    /**
     * 检查用户名是否已存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    private boolean isUsernameExist(String username) {
        return getOne(new LambdaQueryWrapper<AdminUser>()
                .eq(AdminUser::getUsername, username)) != null;
    }

    /**
     * 将实体转换为VO
     *
     * @param adminUser 管理员用户实体
     * @return 管理员用户VO
     */
    private AdminUserVO convertToVO(AdminUser adminUser) {
        if (adminUser == null) {
            return null;
        }
        AdminUserVO vo = new AdminUserVO();
        BeanUtils.copyProperties(adminUser, vo);
        // 不返回密码
        return vo;
    }
}
