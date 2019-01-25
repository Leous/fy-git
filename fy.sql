/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : fy

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 25/01/2019 23:07:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fy_partner
-- ----------------------------
DROP TABLE IF EXISTS `fy_partner`;
CREATE TABLE `fy_partner`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `partner_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `partner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `puid` int(10) NULL DEFAULT NULL COMMENT '用户操作员id',
  `age` int(5) NULL DEFAULT NULL COMMENT '年龄',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `area_code` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政区划代码',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `type` int(11) NULL DEFAULT 0 COMMENT '注册类型，0：后台注册；1：手机号注册；2：账号注册',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `basic_status` int(5) NULL DEFAULT 1 COMMENT '用户基本状态；0：锁定 1：正常 2：注销',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_tel`(`tel`) USING BTREE COMMENT '手机号唯一',
  UNIQUE INDEX `index_partnerId`(`partner_id`) USING BTREE COMMENT '用户ID唯一',
  UNIQUE INDEX `index_email`(`email`) USING BTREE COMMENT '邮箱唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fy_partner
-- ----------------------------
INSERT INTO `fy_partner` VALUES (12, '57024971', 'cnhy', 21, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-12 18:13:22', 1);
INSERT INTO `fy_partner` VALUES (13, '07957709', 'cnchy1', 22, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 00:25:57', 1);
INSERT INTO `fy_partner` VALUES (14, '05791403', 'cnchy', 23, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 00:26:33', 1);
INSERT INTO `fy_partner` VALUES (15, '79025402', 'cnch', 24, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 00:26:38', 1);
INSERT INTO `fy_partner` VALUES (16, '12137803', 'cn', 25, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 01:37:57', 1);
INSERT INTO `fy_partner` VALUES (17, '33685960', 'cnchy12', 26, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 02:36:51', 1);
INSERT INTO `fy_partner` VALUES (18, '02697926', 'cnchy13', 27, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 02:37:44', 1);
INSERT INTO `fy_partner` VALUES (19, '08594881', '1231', 28, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 17:33:00', 1);
INSERT INTO `fy_partner` VALUES (20, '02693893', 'cnchy123', 29, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 17:35:43', 1);
INSERT INTO `fy_partner` VALUES (21, '03603873', 'cnchy14', 30, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 21:46:42', 1);
INSERT INTO `fy_partner` VALUES (22, '27557275', 'cnchy16', 31, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-16 23:46:40', 1);
INSERT INTO `fy_partner` VALUES (23, '45930958', '123', 32, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-18 15:37:44', 1);
INSERT INTO `fy_partner` VALUES (24, '51349548', 'cnchy18', 33, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-18 21:16:38', 1);
INSERT INTO `fy_partner` VALUES (25, '11966899', 'cnchy19', 34, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-18 21:22:00', 1);
INSERT INTO `fy_partner` VALUES (26, '07316747', 'cnchy20', 35, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-19 17:27:19', 1);
INSERT INTO `fy_partner` VALUES (27, '89644191', 'cnchy191', 36, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-19 21:34:25', 1);
INSERT INTO `fy_partner` VALUES (28, '45449577', '1231123', 37, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-20 18:21:00', 1);
INSERT INTO `fy_partner` VALUES (29, '96857851', 'fhhdbdbd', 38, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-20 18:21:16', 1);
INSERT INTO `fy_partner` VALUES (30, '76985396', 'cnchy111', 39, NULL, NULL, NULL, NULL, NULL, 2, '2019-01-20 19:15:55', 1);

-- ----------------------------
-- Table structure for fy_partner_menus
-- ----------------------------
DROP TABLE IF EXISTS `fy_partner_menus`;
CREATE TABLE `fy_partner_menus`  (
  `pmid` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_pmid` int(10) NULL DEFAULT 0 COMMENT '上级菜单ID',
  `menu_level` int(5) NULL DEFAULT NULL COMMENT '菜单等级 0：一级菜单 1：二级菜单 2：三级菜单',
  `menu_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型 D：目录 M：菜单 B：按钮',
  `menu_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单代码',
  `menu_name_ch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称(中文)',
  `menu_name_en` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称(英文)',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `display_order` int(10) NULL DEFAULT NULL COMMENT '菜单显示顺序',
  `status` int(5) NULL DEFAULT NULL COMMENT '菜单状态 1：正常 2：后台关闭',
  PRIMARY KEY (`pmid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fy_partner_menus
-- ----------------------------
INSERT INTO `fy_partner_menus` VALUES (1, 0, 0, 'D', 'fy_blog_index', '博客', 'blog', '/blog/index', 1, 1);
INSERT INTO `fy_partner_menus` VALUES (2, 0, 0, 'D', 'fy_qa_index', '问答区', 'qaArea', '/qa/index', 2, 1);

-- ----------------------------
-- Table structure for fy_partner_user
-- ----------------------------
DROP TABLE IF EXISTS `fy_partner_user`;
CREATE TABLE `fy_partner_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户操作员id',
  `partner_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户号',
  `role_id` int(10) NULL DEFAULT 2 COMMENT '角色ID 默认为普通用户',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_account`(`account`) USING BTREE COMMENT '账号唯一索引',
  UNIQUE INDEX `index_partnerId`(`partner_id`) USING BTREE COMMENT '用户号唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fy_partner_user
-- ----------------------------
INSERT INTO `fy_partner_user` VALUES (21, NULL, 2, 'cnhy', '774244e0143b666eef854f7d62748516', '0af92e', NULL, '2019-01-12 18:13:22');
INSERT INTO `fy_partner_user` VALUES (22, NULL, 2, 'cnchy1', 'd390883cecae468116fa5160460a47ce', 'rwqevz', NULL, '2019-01-16 00:25:57');
INSERT INTO `fy_partner_user` VALUES (23, NULL, 2, 'cnchy', '5232dbe36c441a084474ecc8de092409', '1fbgbe', NULL, '2019-01-16 00:26:33');
INSERT INTO `fy_partner_user` VALUES (24, NULL, 2, 'cnch', '6a66eb2e6ceabe0ecd8e6290859e28e2', 'h2on18', NULL, '2019-01-16 00:26:38');
INSERT INTO `fy_partner_user` VALUES (25, NULL, 2, 'cn', '2205380f02d18fdae6610cfbe4722501', 'qb2dle', NULL, '2019-01-16 01:37:57');
INSERT INTO `fy_partner_user` VALUES (26, NULL, 2, 'cnchy12', '1243340c503781e3f1581f3f81a9b7db', '0nsc37', NULL, '2019-01-16 02:36:51');
INSERT INTO `fy_partner_user` VALUES (27, NULL, 2, 'cnchy13', '3ff3576d5cc1354d4c0ff62985854651', '9d9psw', NULL, '2019-01-16 02:37:44');
INSERT INTO `fy_partner_user` VALUES (28, NULL, 2, '1231', 'c0f60facaa9ca4ec4b963960ddec8805', '92f6ql', NULL, '2019-01-16 17:33:00');
INSERT INTO `fy_partner_user` VALUES (29, NULL, 2, 'cnchy123', 'e2bc356a85cd0be0724284174a77b240', '5dgps4', NULL, '2019-01-16 17:35:43');
INSERT INTO `fy_partner_user` VALUES (30, NULL, 2, 'cnchy14', '87534bebac1036d90cfd4aebfa1d09d4', 'bk7x8q', NULL, '2019-01-16 21:46:42');
INSERT INTO `fy_partner_user` VALUES (31, NULL, 2, 'cnchy16', '1dea1dda93b2a4b29aa343a5fc28bf59', 'y51j5b', NULL, '2019-01-16 23:46:40');
INSERT INTO `fy_partner_user` VALUES (32, NULL, 2, '123', 'c4c2ba8917b633622a6e71684245d7fb', 'jzygpf', NULL, '2019-01-18 15:37:44');
INSERT INTO `fy_partner_user` VALUES (33, NULL, 2, 'cnchy18', '49b2abba7a6eff83c10547ad024ef537', 'vspecl', NULL, '2019-01-18 21:16:38');
INSERT INTO `fy_partner_user` VALUES (34, NULL, 2, 'cnchy19', 'db162bf57e4c6f2903ac25369ba02c58', 'hdfx2y', NULL, '2019-01-18 21:22:00');
INSERT INTO `fy_partner_user` VALUES (35, NULL, 2, 'cnchy20', '934d494740cc5424ec868ab5c5be32a7', 'e64q0v', NULL, '2019-01-19 17:27:19');
INSERT INTO `fy_partner_user` VALUES (36, NULL, 2, 'cnchy191', '165e4117dbf9fc6c4883dc12fd757060', 'p0cwvu', NULL, '2019-01-19 21:34:25');
INSERT INTO `fy_partner_user` VALUES (37, NULL, 2, '1231123', '06f7d57c55404d53b2cf99137b50d20a', '1qi56e', NULL, '2019-01-20 18:21:00');
INSERT INTO `fy_partner_user` VALUES (38, NULL, 2, 'fhhdbdbd', '356d9820588c5c140af25fc93713fd74', 'n2egsy', NULL, '2019-01-20 18:21:16');
INSERT INTO `fy_partner_user` VALUES (39, NULL, 2, 'cnchy111', 'd9411a4a051479e25821789e95a7f8f7', 'l3a25l', NULL, '2019-01-20 19:15:55');

-- ----------------------------
-- Table structure for fy_role
-- ----------------------------
DROP TABLE IF EXISTS `fy_role`;
CREATE TABLE `fy_role`  (
  `role_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色ID 1：管理员 2：普通用户 3：VIP用户',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fy_role
-- ----------------------------
INSERT INTO `fy_role` VALUES (1, '管理员');
INSERT INTO `fy_role` VALUES (2, '普通用户');
INSERT INTO `fy_role` VALUES (3, 'VIP用户');
INSERT INTO `fy_role` VALUES (4, '特殊用户');

-- ----------------------------
-- Table structure for fy_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `fy_role_privilege`;
CREATE TABLE `fy_role_privilege`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色-权限关联表',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  `pmid` int(10) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
