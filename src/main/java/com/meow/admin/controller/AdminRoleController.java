package com.meow.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meow.admin.model.dto.AdminRoleDTO;
import com.meow.admin.model.vo.AdminRoleVO;
import com.meow.admin.service.AdminRoleService;
import com.meow.admin.util.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台角色管理控制器
 */
@Tag(name = "角色管理接口")
@RestController
@RequestMapping("/api/admin/role")
@RequiredArgsConstructor
public class AdminRoleController {
    
    private final AdminRoleService adminRoleService;
    
    @Operation(summary = "分页查询角色列表")
    @GetMapping("/page")
    public Result<IPage<AdminRoleVO>> pageRoles(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
            @Parameter(description = "角色名称") @RequestParam(required = false) String roleName,
            @Parameter(description = "状态") @RequestParam(required = false) Boolean status
    ) {
        IPage<AdminRoleVO> page = adminRoleService.pageRoles(pageNum, pageSize, roleName, status);
        return Result.success(page);
    }
    
    @Operation(summary = "获取角色详情")
    @GetMapping("/{id}")
    public Result<AdminRoleVO> getRoleById(@PathVariable Long id) {
        AdminRoleVO role = adminRoleService.getRoleById(id);
        return Result.success(role);
    }
    
    @Operation(summary = "创建角色")
    @PostMapping
    public Result<AdminRoleVO> createRole(@Valid @RequestBody AdminRoleDTO dto) {
        AdminRoleVO role = adminRoleService.createRole(dto);
        return Result.success(role);
    }
    
    @Operation(summary = "更新角色")
    @PutMapping
    public Result<AdminRoleVO> updateRole(@Valid @RequestBody AdminRoleDTO dto) {
        AdminRoleVO role = adminRoleService.updateRole(dto);
        return Result.success(role);
    }
    
    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteRole(@PathVariable Long id) {
        Boolean success = adminRoleService.deleteRole(id);
        return Result.success(success);
    }
    
    @Operation(summary = "为角色分配菜单权限")
    @PostMapping("/{roleId}/menus")
    public Result<Boolean> assignMenus(
            @PathVariable Long roleId,
            @RequestBody List<Long> menuIds
    ) {
        Boolean success = adminRoleService.assignMenus(roleId, menuIds);
        return Result.success(success);
    }
    
    @Operation(summary = "获取角色的菜单ID列表")
    @GetMapping("/{roleId}/menus")
    public Result<List<Long>> getRoleMenuIds(@PathVariable Long roleId) {
        List<Long> menuIds = adminRoleService.getRoleMenuIds(roleId);
        return Result.success(menuIds);
    }
    
    @Operation(summary = "获取所有角色列表（不分页）")
    @GetMapping("/all")
    public Result<List<AdminRoleVO>> getAllRoles() {
        List<AdminRoleVO> roles = adminRoleService.getAllRoles();
        return Result.success(roles);
    }
}

