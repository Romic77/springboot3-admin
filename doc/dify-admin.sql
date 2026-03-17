/*
 Navicat Premium Dump SQL

 Source Server         : 192.168.11.7
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : 192.168.11.7:3306
 Source Schema         : dify-admin

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 17/03/2026 11:22:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_menu`;
CREATE TABLE `t_admin_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID(0表示一级菜单)',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `menu_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单编码',
  `menu_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '菜单类型(1:目录 2:菜单)',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort_order` int NULL DEFAULT 0 COMMENT '显示顺序',
  `visible` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否显示(0:隐藏 1:显示)',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(0:禁用 1:启用)',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除 1:已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_menu_code`(`menu_code` ASC) USING BTREE,
  INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '后台菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_menu
-- ----------------------------
INSERT INTO `t_admin_menu` VALUES (2, 0, '用户管理', 'USER_MANAGE', 1, '/user', 'Layout', 'User', 2, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 04:19:08', 0);
INSERT INTO `t_admin_menu` VALUES (7, 0, '系统管理', 'SYSTEM_MANAGE', 1, '/system', 'Layout', 'Tools', 10, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-11 05:04:25', 0);
INSERT INTO `t_admin_menu` VALUES (8, 0, '权限管理', 'PERMISSION_MANAGE', 1, '/permission', 'Layout', 'Lock', 9, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-11 05:04:23', 0);
INSERT INTO `t_admin_menu` VALUES (9, 2, '用户列表', 'USER_LIST', 2, '/user/list', 'user/index', 'UserFilled', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (24, 8, '管理员列表', 'ADMIN_LIST', 2, '/system/admin', 'permission/admin-user', 'Avatar', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:36:42', 0);
INSERT INTO `t_admin_menu` VALUES (28, 8, '角色管理', 'ROLE_MANAGE', 2, '/permission/role', 'permission/role', 'UserFilled', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (29, 8, '菜单管理', 'MENU_MANAGE', 2, '/permission/menu', 'permission/menu', 'Menu', 2, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编码',
  `sort_order` int NULL DEFAULT 0 COMMENT '显示顺序',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(0:禁用 1:启用)',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除 1:已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_role_code`(`role_code` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '后台角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES (1, '超级管理员', 'SUPER_ADMIN', 1, 1, '拥有系统所有权限', '2025-11-10 04:19:08', '2025-11-10 04:19:08', 0);
INSERT INTO `t_admin_role` VALUES (2, '普通管理员', 'ADMIN', 2, 1, '拥有基本管理权限', '2025-11-10 04:19:08', '2025-11-10 04:19:08', 0);
INSERT INTO `t_admin_role` VALUES (3, '运营人员', 'OPERATOR', 3, 1, '负责内容运营和用户管理', '2025-11-10 04:19:08', '2025-11-10 04:19:08', 0);
INSERT INTO `t_admin_role` VALUES (4, '测试人员', 'TEST', 0, 1, '', '2026-01-05 09:51:25', '2026-01-05 09:51:25', 0);

-- ----------------------------
-- Table structure for t_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_menu`;
CREATE TABLE `t_admin_role_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_role_menu`(`role_id` ASC, `menu_id` ASC) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE,
  INDEX `idx_menu_id`(`menu_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1770 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '后台角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_role_menu
-- ----------------------------
INSERT INTO `t_admin_role_menu` VALUES (32, 2, 1, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (33, 2, 2, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (34, 2, 3, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (35, 2, 4, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (36, 2, 5, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (37, 2, 6, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (38, 2, 9, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (39, 2, 10, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (40, 2, 11, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (41, 2, 12, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (42, 2, 13, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (43, 2, 14, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (44, 2, 15, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (45, 2, 16, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (46, 2, 17, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (47, 2, 18, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (48, 2, 19, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (49, 2, 20, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (50, 2, 21, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (51, 2, 22, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (52, 2, 23, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (63, 3, 1, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (64, 3, 2, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (65, 3, 3, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (66, 3, 5, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (67, 3, 9, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (68, 3, 10, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (69, 3, 11, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (70, 3, 12, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (71, 3, 13, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (72, 3, 14, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (73, 3, 18, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (74, 3, 19, '2025-11-10 04:19:08');
INSERT INTO `t_admin_role_menu` VALUES (762, 4, 25, '2026-01-05 09:53:01');
INSERT INTO `t_admin_role_menu` VALUES (763, 4, 52, '2026-01-05 09:53:01');
INSERT INTO `t_admin_role_menu` VALUES (764, 4, 7, '2026-01-05 09:53:01');
INSERT INTO `t_admin_role_menu` VALUES (1763, 1, 2, '2026-03-17 11:22:27');
INSERT INTO `t_admin_role_menu` VALUES (1764, 1, 9, '2026-03-17 11:22:27');
INSERT INTO `t_admin_role_menu` VALUES (1765, 1, 8, '2026-03-17 11:22:27');
INSERT INTO `t_admin_role_menu` VALUES (1766, 1, 24, '2026-03-17 11:22:27');
INSERT INTO `t_admin_role_menu` VALUES (1767, 1, 28, '2026-03-17 11:22:27');
INSERT INTO `t_admin_role_menu` VALUES (1768, 1, 29, '2026-03-17 11:22:27');
INSERT INTO `t_admin_role_menu` VALUES (1769, 1, 7, '2026-03-17 11:22:27');

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '加密后的密码',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像URL',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(0:禁用 1:启用)',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除 1:已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '后台管理员用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES (1, 'admin', '$2a$10$EP/sABQVqPDowaQyBYaPGOWCHYBMbQNLvoVp6HY3nZfohs.9/hIeO', '超级管理员', 'admin@meow.com', NULL, NULL, 1, '2026-03-17 11:21:03', NULL, NULL, '2025-11-10 04:19:08', '2025-11-10 04:19:58', 0);
INSERT INTO `t_admin_user` VALUES (2, 'luxueliang', '$2a$10$4FhiG.lJc5UtIVOb8JVE0OIDHJqjqP7dhVOPkFTS3vxf42m2hXsNu', '卢学良', 'xxx@qq.com', NULL, NULL, 1, '2025-11-10 07:13:51', NULL, NULL, '2025-11-10 12:57:12', '2025-11-10 12:57:12', 0);
INSERT INTO `t_admin_user` VALUES (3, 'zhouxiul', '$2a$10$nL.QNkI7WNv89GtlfgV2zeQw6ylcb4hlkU27oeS6yrHg9Qn5Jk2/a', '周秀玲', '111@qq.com', NULL, NULL, 1, NULL, NULL, NULL, '2026-01-05 09:52:35', '2026-01-05 09:52:35', 0);

-- ----------------------------
-- Table structure for t_admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user_role`;
CREATE TABLE `t_admin_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_role`(`user_id` ASC, `role_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '后台用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_user_role
-- ----------------------------
INSERT INTO `t_admin_user_role` VALUES (1, 1, 1, '2025-11-10 04:19:08');
INSERT INTO `t_admin_user_role` VALUES (3, 2, 2, '2025-11-25 15:15:47');
INSERT INTO `t_admin_user_role` VALUES (4, 3, 4, '2026-01-05 09:52:42');

SET FOREIGN_KEY_CHECKS = 1;
