package com.meow.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meow.admin.model.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台管理员用户Mapper接口
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
}

