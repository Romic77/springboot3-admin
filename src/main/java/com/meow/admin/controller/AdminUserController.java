package com.meow.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meow.admin.model.dto.AdminLoginDTO;
import com.meow.admin.model.dto.AdminRegisterDTO;
import com.meow.admin.model.dto.AdminUpdatePasswordDTO;
import com.meow.admin.model.dto.AdminUserDTO;
import com.meow.admin.model.vo.AdminUserVO;
import com.meow.admin.service.AdminUserService;
import com.meow.admin.util.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台管理员用户控制器
 */
@Tag(name = "管理员用户接口")
@RestController
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor
public class AdminUserController {
    
    private final AdminUserService adminUserService;
    
    @Operation(summary = "管理员登录")
    @PostMapping("/login")
    public Result<AdminUserVO> login(@Valid @RequestBody AdminLoginDTO loginDTO) {
        AdminUserVO adminUserVO = adminUserService.login(loginDTO);
        return Result.success(adminUserVO);
    }
    
    @Operation(summary = "管理员注册")
    @PostMapping("/register")
    public Result<AdminUserVO> register(@Valid @RequestBody AdminRegisterDTO registerDTO) {
        AdminUserVO adminUserVO = adminUserService.register(registerDTO);
        return Result.success(adminUserVO);
    }
    
    @Operation(summary = "管理员登出")
    @PostMapping("/logout")
    public Result<Void> logout() {
        StpUtil.logout();
        return Result.success();
    }
    
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/info")
    public Result<AdminUserVO> getCurrentUserInfo() {
        AdminUserVO userInfo = adminUserService.getCurrentUserInfo();
        return Result.success(userInfo);
    }
    
    @Operation(summary = "分页查询管理员列表")
    @GetMapping("/page")
    public Result<IPage<AdminUserVO>> pageAdminUsers(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
            @Parameter(description = "用户名") @RequestParam(required = false) String username,
            @Parameter(description = "状态") @RequestParam(required = false) Boolean status
    ) {
        IPage<AdminUserVO> page = adminUserService.pageAdminUsers(pageNum, pageSize, username, status);
        return Result.success(page);
    }
    
    @Operation(summary = "根据ID获取管理员信息")
    @GetMapping("/{id}")
    public Result<AdminUserVO> getAdminUserById(@PathVariable Long id) {
        AdminUserVO adminUserVO = adminUserService.getAdminUserById(id);
        return Result.success(adminUserVO);
    }
    
    @Operation(summary = "创建管理员用户")
    @PostMapping
    public Result<AdminUserVO> createAdminUser(@Valid @RequestBody AdminUserDTO dto) {
        AdminUserVO adminUserVO = adminUserService.createAdminUser(dto);
        return Result.success(adminUserVO);
    }
    
    @Operation(summary = "更新管理员用户")
    @PutMapping
    public Result<AdminUserVO> updateAdminUser(@Valid @RequestBody AdminUserDTO dto) {
        AdminUserVO adminUserVO = adminUserService.updateAdminUser(dto);
        return Result.success(adminUserVO);
    }
    
    @Operation(summary = "删除管理员用户")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteAdminUser(@PathVariable Long id) {
        Boolean success = adminUserService.deleteAdminUser(id);
        return Result.success(success);
    }
    
    @Operation(summary = "修改密码")
    @PutMapping("/password")
    public Result<Void> updatePassword(@Valid @RequestBody AdminUpdatePasswordDTO dto) {
        Long adminId = StpUtil.getLoginIdAsLong();
        adminUserService.updatePassword(dto, adminId);
        return Result.success();
    }
    
    @Operation(summary = "为用户分配角色")
    @PostMapping("/{userId}/roles")
    public Result<Boolean> assignRoles(
            @PathVariable Long userId,
            @RequestBody List<Long> roleIds
    ) {
        Boolean success = adminUserService.assignRoles(userId, roleIds);
        return Result.success(success);
    }
    
    @Operation(summary = "获取用户的角色ID列表")
    @GetMapping("/{userId}/roles")
    public Result<List<Long>> getUserRoleIds(@PathVariable Long userId) {
        List<Long> roleIds = adminUserService.getUserRoleIds(userId);
        return Result.success(roleIds);
    }
}

