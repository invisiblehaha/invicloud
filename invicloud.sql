/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : invicloud

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 04/07/2018 04:58:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crm_category
-- ----------------------------
DROP TABLE IF EXISTS `crm_category`;
CREATE TABLE `crm_category`  (
  `category_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父分类ID',
  `category_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `sort` int(9) NULL DEFAULT NULL COMMENT '排序',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '目录类型 2=二级目录/1=一级目录/0=总目录',
  `status` tinyint(2) NULL DEFAULT 0 COMMENT '状态 1=显示/0=隐藏',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_customer
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer`;
CREATE TABLE `crm_customer`  (
  `customer_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '顾客ID',
  `customer_token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人脸标识',
  `real_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别 0=保密/1=男/2=女',
  `age` tinyint(3) NULL DEFAULT 0 COMMENT '年龄',
  `pic_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '顾客图片',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 0=冻结/1=正常',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `regeist_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `rank` tinyint(4) NULL DEFAULT NULL COMMENT '会员级别',
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_log
-- ----------------------------
DROP TABLE IF EXISTS `crm_log`;
CREATE TABLE `crm_log`  (
  `log_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '管理员ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `spend_time` int(11) NULL DEFAULT NULL COMMENT '耗时',
  `method` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  `user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户代理',
  `user_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户IP',
  `opt_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求内容',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_menu
-- ----------------------------
DROP TABLE IF EXISTS `crm_menu`;
CREATE TABLE `crm_menu`  (
  `menu_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父目录ID',
  `menu_type` tinyint(2) NULL DEFAULT NULL COMMENT '权限类型 0=菜单/1=功能/2=操作',
  `menu_sign` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目录标识',
  `menu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目录名称',
  `sort` int(9) NULL DEFAULT NULL COMMENT '排序码',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标名称',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态 0=隐藏/1=显示',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '目录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_order
-- ----------------------------
DROP TABLE IF EXISTS `crm_order`;
CREATE TABLE `crm_order`  (
  `order_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `customer_id` int(11) UNSIGNED NOT NULL COMMENT '消费者ID',
  `pay_type` tinyint(1) NULL DEFAULT 1 COMMENT '支付方式 0=现金支付，1=移动支付，2=刷卡支付',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `buy_amount` int(11) NULL DEFAULT 0 COMMENT '商品总数',
  `pay_amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '支付金额',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `customer_id`(`customer_id`) USING BTREE,
  CONSTRAINT `crm_order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `crm_customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_order_product
-- ----------------------------
DROP TABLE IF EXISTS `crm_order_product`;
CREATE TABLE `crm_order_product`  (
  `order_id` bigint(20) UNSIGNED NOT NULL COMMENT '订单ID',
  `product_id` int(11) UNSIGNED NOT NULL COMMENT '商品ID',
  `buy_amount` int(11) NULL DEFAULT NULL COMMENT '购买数量',
  `pay_amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '购买金额',
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `crm_order_product_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `crm_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `crm_order_product_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `crm_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_product
-- ----------------------------
DROP TABLE IF EXISTS `crm_product`;
CREATE TABLE `crm_product`  (
  `product_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `product_introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品简介',
  `stock` int(11) NULL DEFAULT 0 COMMENT '库存',
  `status` tinyint(2) NULL DEFAULT 0 COMMENT '商品状态：0=下架/1=上架',
  `pic_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '展示图片',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `crm_product_attribute`;
CREATE TABLE `crm_product_attribute`  (
  `product_id` int(11) UNSIGNED NOT NULL COMMENT '产品ID',
  `specification_attribute_id` int(11) UNSIGNED NOT NULL COMMENT '规格属性ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `specification_attribute_id`(`specification_attribute_id`) USING BTREE,
  CONSTRAINT `crm_product_attribute_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `crm_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `crm_product_attribute_ibfk_2` FOREIGN KEY (`specification_attribute_id`) REFERENCES `crm_specification_attribute` (`specification_attribute_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_product_category
-- ----------------------------
DROP TABLE IF EXISTS `crm_product_category`;
CREATE TABLE `crm_product_category`  (
  `product_id` int(11) UNSIGNED NOT NULL COMMENT '商品ID',
  `category_id` int(11) UNSIGNED NOT NULL COMMENT '分类ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `crm_product_category_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `crm_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `crm_product_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `crm_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_role
-- ----------------------------
DROP TABLE IF EXISTS `crm_role`;
CREATE TABLE `crm_role`  (
  `role_id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_sign` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `is_system` tinyint(2) NULL DEFAULT 1 COMMENT '系统级数据不能更改 0=否/1=是',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crm_role
-- ----------------------------
INSERT INTO `crm_role` VALUES (1, '超级管理员', 'admin', 1, '超级管理员', '2018-07-03 14:26:47', 1, NULL, NULL);

-- ----------------------------
-- Table structure for crm_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `crm_role_menu`;
CREATE TABLE `crm_role_menu`  (
  `role_id` int(5) UNSIGNED NOT NULL COMMENT '角色编号',
  `menu_id` int(11) UNSIGNED NOT NULL COMMENT '权限编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者ID',
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE,
  CONSTRAINT `crm_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `crm_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `crm_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `crm_menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_specification
-- ----------------------------
DROP TABLE IF EXISTS `crm_specification`;
CREATE TABLE `crm_specification`  (
  `specification_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '规格ID',
  `category_id` int(11) UNSIGNED NOT NULL COMMENT '分类ID',
  `specification_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  PRIMARY KEY (`specification_id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `crm_specification_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `crm_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规格表\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_specification_attribute
-- ----------------------------
DROP TABLE IF EXISTS `crm_specification_attribute`;
CREATE TABLE `crm_specification_attribute`  (
  `specification_attribute_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '规格属性ID',
  `specification_id` int(11) UNSIGNED NOT NULL COMMENT '规格ID',
  `attribute_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格属性内容',
  PRIMARY KEY (`specification_attribute_id`) USING BTREE,
  INDEX `specification_id`(`specification_id`) USING BTREE,
  CONSTRAINT `crm_specification_attribute_ibfk_1` FOREIGN KEY (`specification_id`) REFERENCES `crm_specification` (`specification_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规格属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crm_user
-- ----------------------------
DROP TABLE IF EXISTS `crm_user`;
CREATE TABLE `crm_user`  (
  `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `login_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户密码',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别 0=保密/1=男/2=女',
  `age` tinyint(3) NULL DEFAULT 0 COMMENT '年龄',
  `pic_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户图片',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 0=冻结/1=正常',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '创建者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crm_user
-- ----------------------------
INSERT INTO `crm_user` VALUES (1, 'admin', 'd81c31b9348c3da513177a781703767e', 'Dts7jk', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for crm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `crm_user_role`;
CREATE TABLE `crm_user_role`  (
  `role_id` int(5) UNSIGNED NOT NULL COMMENT '角色ID',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '管理员ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者ID',
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `crm_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `crm_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `crm_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `crm_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crm_user_role
-- ----------------------------
INSERT INTO `crm_user_role` VALUES (1, 1, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
