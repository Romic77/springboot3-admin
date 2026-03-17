package com.meow.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.meow.admin.model.dto.AdminMenuDTO;
import com.meow.admin.model.vo.AdminMenuVO;
import com.meow.admin.service.AdminMenuService;
import com.meow.admin.util.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台菜单管理控制器
 */
@Tag(name = "菜单管理接口")
@RestController
@RequestMapping("/api/admin/menu")
@RequiredArgsConstructor
public class AdminMenuController {
    
    private final AdminMenuService adminMenuService;
    
    @Operation(summary = "获取菜单树形列表")
    @GetMapping("/tree")
    public Result<List<AdminMenuVO>> getMenuTree(
            @Parameter(description = "菜单名称") @RequestParam(required = false) String menuName,
            @Parameter(description = "状态") @RequestParam(required = false) Boolean status
    ) {
        List<AdminMenuVO> tree = adminMenuService.getMenuTree(menuName, status);
        return Result.success(tree);
    }
    
    @Operation(summary = "获取菜单详情")
    @GetMapping("/{id}")
    public Result<AdminMenuVO> getMenuById(@PathVariable Long id) {
        AdminMenuVO menu = adminMenuService.getMenuById(id);
        return Result.success(menu);
    }
    
    @Operation(summary = "创建菜单")
    @PostMapping
    public Result<AdminMenuVO> createMenu(@Valid @RequestBody AdminMenuDTO dto) {
        AdminMenuVO menu = adminMenuService.createMenu(dto);
        return Result.success(menu);
    }
    
    @Operation(summary = "更新菜单")
    @PutMapping
    public Result<AdminMenuVO> updateMenu(@Valid @RequestBody AdminMenuDTO dto) {
        AdminMenuVO menu = adminMenuService.updateMenu(dto);
        return Result.success(menu);
    }
    
    @Operation(summary = "删除菜单")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteMenu(@PathVariable Long id) {
        Boolean success = adminMenuService.deleteMenu(id);
        return Result.success(success);
    }
    
    @Operation(summary = "获取当前用户的菜单树")
    @GetMapping("/user/tree")
    public Result<List<AdminMenuVO>> getUserMenuTree() {
        Long userId = StpUtil.getLoginIdAsLong();
        List<AdminMenuVO> tree = adminMenuService.getMenuTreeByUserId(userId);
        return Result.success(tree);
    }
    
    @Operation(summary = "获取所有菜单列表（不分页）")
    @GetMapping("/all")
    public Result<List<AdminMenuVO>> getAllMenus() {
        List<AdminMenuVO> menus = adminMenuService.getAllMenus();
        return Result.success(menus);
    }
}

