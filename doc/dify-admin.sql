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

 Date: 17/03/2026 11:03:16
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
INSERT INTO `t_admin_menu` VALUES (3, 0, '资源管理', 'RESOURCE_MANAGE', 1, '/resource', 'Layout', 'Files', 3, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 04:19:08', 0);
INSERT INTO `t_admin_menu` VALUES (4, 0, '生图管理', 'IMAGE_MANAGE', 1, '/image', 'Layout', 'Picture', 5, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-11 04:29:54', 0);
INSERT INTO `t_admin_menu` VALUES (5, 0, '分类管理', 'CATEGORY_MANAGE', 1, '/category', 'Layout', 'Menu', 4, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-11 04:29:56', 0);
INSERT INTO `t_admin_menu` VALUES (6, 0, '订阅管理', 'SUBSCRIPTION_MANAGE', 1, '/subscription', 'Layout', 'ShoppingCart', 6, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 04:19:08', 0);
INSERT INTO `t_admin_menu` VALUES (7, 0, '系统管理', 'SYSTEM_MANAGE', 1, '/system', 'Layout', 'Tools', 10, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-11 05:04:25', 0);
INSERT INTO `t_admin_menu` VALUES (8, 0, '权限管理', 'PERMISSION_MANAGE', 1, '/permission', 'Layout', 'Lock', 9, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-11 05:04:23', 0);
INSERT INTO `t_admin_menu` VALUES (9, 2, '用户列表', 'USER_LIST', 2, '/user/list', 'user/index', 'UserFilled', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (10, 2, '用户反馈', 'USER_FEEDBACK', 2, '/user/feedback', 'user/feedback', 'ChatDotRound', 2, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (11, 3, '基础样式', 'STYLE_BASE', 2, '/resource/style', 'resource/style', 'Brush', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (12, 3, '平台样式', 'STYLE_PLATFORM', 2, '/resource/platform-style', 'resource/style-variant', 'Platform', 2, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (14, 3, '上新弹窗', 'POPUP_MANAGE', 2, '/resource/popup', 'resource/popup-new-item', 'Notification', 4, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (15, 4, '生图记录', 'IMAGE_RECORD', 2, '/image/record', 'image-generation/record', 'Document', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (16, 4, '生图统计', 'IMAGE_STATS', 2, '/image/stats', 'image-generation/statistics', 'DataAnalysis', 2, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (17, 4, '反馈管理', 'IMAGE_FEEDBACK', 2, '/image/feedback', 'image-generation/feedback', 'ChatLineRound', 3, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (18, 5, '分类管理', 'CATEGORY_LIST', 2, '/category/list', 'category/index', 'Grid', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (19, 5, '风格分类', 'STYLE_CATEGORY', 2, '/category/style', 'category/style-category', 'Collection', 2, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (20, 6, '订阅产品', 'SUBSCRIPTION_PRODUCT', 2, '/subscription/product', 'subscription/product', 'Goods', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (21, 6, '计划详情', 'SUBSCRIPTION_PLAN', 2, '/subscription/plan', 'subscription/plan-detail', 'List', 2, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (22, 6, '订阅状态', 'SUBSCRIPTION_STATUS', 2, '/subscription/status', 'subscription/subscription-status', 'View', 3, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (23, 6, '支付日志', 'PAYMENT_LOG', 2, '/subscription/payment-log', 'subscription/payment-log', 'Tickets', 4, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (24, 8, '管理员列表', 'ADMIN_LIST', 2, '/system/admin', 'permission/admin-user', 'Avatar', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:36:42', 0);
INSERT INTO `t_admin_menu` VALUES (25, 7, '配置管理', 'CONFIG_MANAGE', 2, '/system/config', 'system/config', 'Setting', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-11 05:07:54', 0);
INSERT INTO `t_admin_menu` VALUES (26, 7, '版本管理', 'VERSION_MANAGE', 2, '/system/app-version', 'system/app-version', 'Promotion', 2, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-11 05:07:56', 0);
INSERT INTO `t_admin_menu` VALUES (27, 7, '数据生成', 'DATA_SYNC', 2, '/system/data-sync', 'system/data-sync', 'DocumentAdd', 4, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:36:07', 0);
INSERT INTO `t_admin_menu` VALUES (28, 8, '角色管理', 'ROLE_MANAGE', 2, '/permission/role', 'permission/role', 'UserFilled', 1, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (29, 8, '菜单管理', 'MENU_MANAGE', 2, '/permission/menu', 'permission/menu', 'Menu', 2, 1, 1, NULL, '2025-11-10 04:19:08', '2025-11-10 06:14:52', 0);
INSERT INTO `t_admin_menu` VALUES (32, 0, '标签管理', 'TAG_MANAGE', 1, '/tag-management', 'Layout', 'Collection', 3, 1, 1, NULL, '2025-11-11 04:25:47', '2025-11-11 05:03:56', 0);
INSERT INTO `t_admin_menu` VALUES (33, 32, '标签管理', 'TAG_LIST', 2, '/resource/tag', 'resource/tag', 'CollectionTag', 1, 1, 1, NULL, '2025-11-11 04:27:34', '2025-11-11 04:45:52', 0);
INSERT INTO `t_admin_menu` VALUES (34, 32, '风格标签管理', 'STYLE_TAG', 2, '/resource/style-tag', 'resource/style-tag', 'PriceTag', 2, 1, 1, NULL, '2025-11-11 04:28:29', '2025-11-11 04:45:51', 0);
INSERT INTO `t_admin_menu` VALUES (35, 6, '订阅配置', 'SUBSCRIPTION_CONFIG_CENTER', 2, '/subscription/config-center', 'subscription/config-center', 'Setting', 0, 1, 1, NULL, '2025-11-11 04:45:46', '2025-11-11 07:39:15', 0);
INSERT INTO `t_admin_menu` VALUES (36, 6, '促销优惠', 'PLAN_OFFER', 2, '/subscription/plan-offer', 'subscription/plan-offer', 'Present', 3, 1, 1, NULL, '2025-11-11 04:50:04', '2025-11-11 04:50:35', 0);
INSERT INTO `t_admin_menu` VALUES (43, 3, 'Credits消耗规则', 'CREDITS_CONSUME_RULE', 2, '/resource/credits-consume-rule', '/resource/credits-consume-rule', 'CreditCard', 3, 1, 1, NULL, '2025-11-11 05:07:01', '2026-02-25 07:45:48', 0);
INSERT INTO `t_admin_menu` VALUES (44, 7, '缓存管理', 'CACHE_MANAGE', 2, '/system/cache', '/system/cache', 'Coin', 5, 1, 1, NULL, '2025-11-20 07:17:49', '2025-11-20 07:22:00', 0);
INSERT INTO `t_admin_menu` VALUES (45, 56, '自定义提示词', 'CUSTOM_STYLE_PROMPT_MANAGE', 2, '/editfeature/edit-feature-prompt', '/editfeature/edit-feature-prompt', 'CopyDocument', 7, 1, 1, NULL, '2025-11-25 07:11:08', '2026-02-13 07:03:41', 0);
INSERT INTO `t_admin_menu` VALUES (47, 56, '自定义编辑', 'EDIT_FEATURE', 2, '/editfeature/edit-feature', '/editfeature/edit-feature', 'Edit', 6, 1, 1, NULL, '2025-11-25 07:11:08', '2026-02-13 06:51:20', 0);
INSERT INTO `t_admin_menu` VALUES (52, 7, '优惠码', 'COUPON_LIST', 2, '/system/coupon', '/system/coupon', 'CreditCard', 9, 1, 1, NULL, '2026-01-04 06:22:35', '2026-01-04 06:25:05', 0);
INSERT INTO `t_admin_menu` VALUES (56, 0, '自定义编辑', 'EDIT_FEATURE_MANAGE', 1, '/editfeature/edit-feature', 'Layout', 'Edit', 4, 1, 1, NULL, '2026-02-04 10:16:59', '2026-02-13 06:51:03', 0);
INSERT INTO `t_admin_menu` VALUES (57, 5, '平台分类', 'CATEGORY_VARIANT', 2, '/category/category-variant', '/category/category-variant', 'Grid', 2, 1, 1, NULL, '2026-02-04 12:32:01', '2026-02-05 07:22:16', 0);
INSERT INTO `t_admin_menu` VALUES (58, 3, '样式数字人', 'STYLE_DIGITAL_HUMAN', 2, '/resource/style-digital-human', '/resource/style-digital-human', 'UserFilled', 5, 1, 1, NULL, '2026-02-10 06:16:48', '2026-02-10 07:39:24', 0);
INSERT INTO `t_admin_menu` VALUES (59, 78, '样式音色', 'STYLE_VOICE', 2, '/voice/style-voice', '/voice/style-voice', 'Microphone', 6, 1, 1, NULL, '2026-02-10 06:16:48', '2026-02-25 07:55:52', 0);
INSERT INTO `t_admin_menu` VALUES (60, 3, '样式头像', 'STYLE_PERSON', 2, '/resource/style-person', '/resource/style-person', 'User', 7, 1, 1, NULL, '2026-02-10 06:16:48', '2026-02-10 08:46:57', 0);
INSERT INTO `t_admin_menu` VALUES (61, 3, '样式用户提示词', 'STYLE_USER_PROMPT', 2, '/resource/style-user-prompt', '/resource/style-user-prompt', 'Document', 9, 1, 1, NULL, '2026-02-10 06:16:48', '2026-02-10 09:43:32', 0);
INSERT INTO `t_admin_menu` VALUES (62, 3, '样式提示词', 'STYLE_PROMPT', 2, '/resource/style-prompt', '/resource/style-prompt', 'DocumentCopy', 8, 1, 1, NULL, '2026-02-10 06:16:48', '2026-02-10 09:43:35', 0);
INSERT INTO `t_admin_menu` VALUES (63, 0, '模型管理', 'MODEL', 1, '/model', 'Layout', 'Grid', 8, 1, 1, NULL, '2026-02-10 10:05:38', '2026-02-10 10:07:04', 0);
INSERT INTO `t_admin_menu` VALUES (64, 63, '模型管理', 'MODEL_LIST', 2, '/model/model', '/model/model', 'Grid', 1, 1, 1, NULL, '2026-02-10 10:18:10', '2026-02-10 10:38:55', 0);
INSERT INTO `t_admin_menu` VALUES (65, 63, '模型参数', 'MODEL_PARAM', 2, '/model/model-param', '/model/model-param', 'Setting', 2, 1, 1, NULL, '2026-02-10 10:38:48', '2026-02-11 01:50:27', 0);
INSERT INTO `t_admin_menu` VALUES (66, 63, '模型参数枚举', 'MODEL_PARAM_ENUM', 2, '/model/model-param-enum', '/model/model-param-enum', 'List', 3, 1, 1, NULL, '2026-02-10 10:38:48', '2026-02-11 02:22:19', 0);
INSERT INTO `t_admin_menu` VALUES (67, 63, '模型参数映射', 'MODEL_PARAM_MAPPING', 2, '/model/model-param-mapping', '/model/model-param-mapping', 'Connection', 4, 1, 1, NULL, '2026-02-11 02:11:36', '2026-02-11 04:01:50', 0);
INSERT INTO `t_admin_menu` VALUES (68, 63, '模型参数枚举映射', 'MODEL_PARAM_ENUM_MAPPING', 2, '/model/model-param-enum-mapping', '/model/model-param-enum-mapping', 'Share', 5, 1, 1, NULL, '2026-02-11 02:21:14', '2026-02-11 04:01:53', 0);
INSERT INTO `t_admin_menu` VALUES (69, 63, '模型参数枚举关联', 'MODEL_PARAM_ENUM_RELATION', 2, '/model/model-param-enum-relation', '/model/model-param-enum-relation', 'Link', 6, 1, 1, NULL, '2026-02-11 02:21:14', '2026-02-11 04:01:54', 0);
INSERT INTO `t_admin_menu` VALUES (70, 63, '样式模型关联', 'STYLE_MODEL', 2, '/model/style-model', '/model/style-model', 'Link', 7, 1, 1, NULL, '2026-02-11 02:21:14', '2026-02-11 04:01:54', 0);
INSERT INTO `t_admin_menu` VALUES (71, 63, '模型参数消耗', 'MODEL_PARAM_CREDITS_RULE', 2, '/model/credits-param-rule', '/model/credits-param-rule', 'Coin', 8, 1, 1, NULL, '2026-02-11 02:21:14', '2026-02-11 12:07:48', 0);
INSERT INTO `t_admin_menu` VALUES (72, 56, '自定义样式', 'EDIT_FEATURE_STYLE', 2, '/editfeature/edit-feature-style', '/editfeature/edit-feature-style', 'Edit', 7, 1, 1, NULL, '2025-11-25 07:11:08', '2026-02-13 06:51:20', 0);
INSERT INTO `t_admin_menu` VALUES (73, 56, '自定义背景', 'EDIT_FEATURE_BACKGROUND', 2, '/editfeature/edit-feature-background', '/editfeature/edit-feature-background', 'Edit', 8, 1, 1, NULL, '2025-11-25 07:11:08', '2026-02-13 06:51:20', 0);
INSERT INTO `t_admin_menu` VALUES (74, 3, '数字人', 'DIGITAL_HUMAN', 2, '/resource/digital-human', '/resource/digital-human', 'User', 9, 1, 1, NULL, '2026-02-13 07:48:48', '2026-02-25 07:54:49', 0);
INSERT INTO `t_admin_menu` VALUES (75, 78, '音色厂商', 'VENDOR', 2, '/voice/vendor', '/voice/vendor', 'OfficeBuilding', 1, 1, 1, NULL, '2026-02-13 07:48:48', '2026-02-25 07:56:13', 0);
INSERT INTO `t_admin_menu` VALUES (76, 78, '音色', 'VOICE', 2, '/voice/voice', '/voice/voice', 'Microphone', 2, 1, 1, NULL, '2026-02-13 08:26:23', '2026-02-25 07:56:14', 0);
INSERT INTO `t_admin_menu` VALUES (77, 78, '音色厂商关联', 'VOICE_VENDOR_MAPPING', 2, '/voice/voice-vendor-mapping', '/voice/voice-vendor-mapping', 'Link', 3, 1, 1, NULL, '2026-02-13 08:27:04', '2026-02-25 07:56:15', 0);
INSERT INTO `t_admin_menu` VALUES (78, 0, '音色管理', 'VOICE_MANAGE', 1, '/voice', 'Layout', 'Microphone', 5, 1, 1, NULL, '2026-02-25 07:53:22', '2026-02-25 07:55:15', 0);
INSERT INTO `t_admin_menu` VALUES (79, 0, '虚拟换装', 'VIRTUAL_CLOTHING', 1, '/virtual-clothing', 'Layout', 'MagicStick', 6, 1, 1, NULL, '2026-02-26 03:44:24', '2026-02-26 07:34:30', 0);
INSERT INTO `t_admin_menu` VALUES (80, 79, '部位', 'PART', 2, '/virtual-clothing/part', '/virtual-clothing/part', 'Grid', 1, 1, 1, NULL, '2026-02-26 03:44:57', '2026-02-26 07:34:33', 0);
INSERT INTO `t_admin_menu` VALUES (81, 79, '尺寸', 'SIZE', 2, '/virtual-clothing/size', '/virtual-clothing/size', 'Expand', 2, 1, 1, NULL, '2026-02-26 03:50:49', '2026-02-26 07:34:37', 0);
INSERT INTO `t_admin_menu` VALUES (82, 79, '样式部位关联', 'SYTLE-PART', 2, '/virtual-clothing/style-part', '/virtual-clothing/style-part', 'Connection', 3, 1, 1, NULL, '2026-02-26 04:33:59', '2026-02-26 07:34:40', 0);
INSERT INTO `t_admin_menu` VALUES (83, 79, '风格尺寸关联', 'SYTLE-SIZE', 2, '/virtual-clothing/style-size', '/virtual-clothing/style-size', 'SetUp', 3, 1, 1, NULL, '2026-02-26 04:33:59', '2026-02-26 07:34:44', 0);
INSERT INTO `t_admin_menu` VALUES (84, 3, '人物管理', 'PERSON_MANAGE', 2, '/resource/person', '/resource/person', 'User', 10, 1, 1, NULL, '2026-03-11 09:35:53', '2026-03-11 10:07:37', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1763 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '后台角色菜单关联表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `t_admin_role_menu` VALUES (1698, 1, 2, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1699, 1, 9, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1700, 1, 10, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1701, 1, 32, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1702, 1, 33, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1703, 1, 34, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1704, 1, 3, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1705, 1, 11, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1706, 1, 12, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1707, 1, 43, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1708, 1, 14, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1709, 1, 58, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1710, 1, 60, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1711, 1, 62, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1712, 1, 74, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1713, 1, 61, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1714, 1, 56, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1715, 1, 47, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1716, 1, 72, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1717, 1, 45, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1718, 1, 73, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1719, 1, 5, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1720, 1, 18, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1721, 1, 57, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1722, 1, 19, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1723, 1, 4, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1724, 1, 15, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1725, 1, 16, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1726, 1, 17, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1727, 1, 78, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1728, 1, 75, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1729, 1, 76, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1730, 1, 77, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1731, 1, 59, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1732, 1, 6, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1733, 1, 35, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1734, 1, 20, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1735, 1, 21, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1736, 1, 22, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1737, 1, 36, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1738, 1, 23, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1739, 1, 79, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1740, 1, 80, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1741, 1, 81, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1742, 1, 82, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1743, 1, 83, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1744, 1, 63, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1745, 1, 64, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1746, 1, 65, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1747, 1, 66, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1748, 1, 67, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1749, 1, 68, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1750, 1, 69, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1751, 1, 70, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1752, 1, 71, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1753, 1, 8, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1754, 1, 28, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1755, 1, 24, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1756, 1, 29, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1757, 1, 7, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1758, 1, 25, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1759, 1, 26, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1760, 1, 27, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1761, 1, 44, '2026-02-26 14:10:41');
INSERT INTO `t_admin_role_menu` VALUES (1762, 1, 52, '2026-02-26 14:10:41');

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
INSERT INTO `t_admin_user` VALUES (1, 'admin', '$2a$10$EP/sABQVqPDowaQyBYaPGOWCHYBMbQNLvoVp6HY3nZfohs.9/hIeO', '超级管理员', 'admin@meow.com', NULL, NULL, 1, '2026-03-13 10:57:08', NULL, NULL, '2025-11-10 04:19:08', '2025-11-10 04:19:58', 0);
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
