/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : db_springboot_teach_02

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-09-29 22:48:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for appendix
-- ----------------------------
DROP TABLE IF EXISTS `appendix`;
CREATE TABLE `appendix` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '附件名',
  `size` bigint(20) DEFAULT NULL COMMENT '大小',
  `module_type` varchar(255) DEFAULT NULL COMMENT '所属模块',
  `record_id` int(11) DEFAULT NULL COMMENT '所属模块的记录id',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除(1=是；0=否)',
  `location` varchar(255) DEFAULT NULL COMMENT '附件位置',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sort_by` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='附件表';

-- ----------------------------
-- Records of appendix
-- ----------------------------
INSERT INTO `appendix` VALUES ('6', 'timg (22).jpg', '34035', 'orderRecord', '11', '0', '\\orderRecord\\20180923\\20180923124056.jpg', '2018-09-23 12:40:56', '2018-09-23 12:42:22', '0');
INSERT INTO `appendix` VALUES ('7', 'SpringBoot实战视频教程讲义之阿修罗主讲.docx', '17848', 'orderRecord', '11', '0', '\\orderRecord\\20180923\\20180923124118.docx', '2018-09-23 12:41:18', '2018-09-23 12:42:23', '0');
INSERT INTO `appendix` VALUES ('8', '6 (2).jpg', '43164', 'orderRecord', '11', '0', '\\orderRecord\\20180923\\20180923124736.jpg', '2018-09-23 12:47:36', '2018-09-23 12:47:46', '0');

-- ----------------------------
-- Table structure for item_record
-- ----------------------------
DROP TABLE IF EXISTS `item_record`;
CREATE TABLE `item_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `item_no` varchar(25) DEFAULT NULL COMMENT '商品编号',
  `stock` bigint(255) DEFAULT NULL COMMENT '商品库存',
  `purchase_date` date DEFAULT NULL COMMENT '采购日期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品记录表';

-- ----------------------------
-- Records of item_record
-- ----------------------------
INSERT INTO `item_record` VALUES ('1', '抱枕', '10010', '856', '2018-09-05', '2018-09-26 22:16:02', null);
INSERT INTO `item_record` VALUES ('2', '座椅', '10011', '128', '2018-09-13', '2018-09-26 22:16:42', '2018-09-26 22:16:49');
INSERT INTO `item_record` VALUES ('3', '平衡车', '10012', '76', '2018-09-20', '2018-09-26 22:17:05', null);

-- ----------------------------
-- Table structure for order_record
-- ----------------------------
DROP TABLE IF EXISTS `order_record`;
CREATE TABLE `order_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) NOT NULL COMMENT '订单编号',
  `order_type` varchar(255) DEFAULT NULL COMMENT '订单类型',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_record
-- ----------------------------
INSERT INTO `order_record` VALUES ('1', '10010', '物流2', '2018-07-19 17:28:08', '2018-07-20 10:33:10');
INSERT INTO `order_record` VALUES ('8', '10011', '物流3', '2018-09-13 23:12:31', null);
INSERT INTO `order_record` VALUES ('9', '10013', '新的类型', '2018-09-13 23:27:59', null);
INSERT INTO `order_record` VALUES ('10', '10018', '新的类型8', '2018-09-13 23:29:36', '2018-09-13 23:36:10');
INSERT INTO `order_record` VALUES ('11', '10019', '附件模块记录', '2018-09-23 11:55:33', null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `price` double(15,2) DEFAULT NULL COMMENT '单价',
  `stock` int(11) DEFAULT NULL COMMENT '库存量',
  `remark` varchar(1000) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL COMMENT '采购日期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除(1=是；0=否)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='产品信息表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('48', '联想笔记本', '台', '4500.00', '120', '好', '2018-05-02', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('49', 'IBM服务器2018', '个', '1400.00', '120', '不错22', '2018-07-14', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('50', '路由器', '台', '10.00', '5', '很好', '2018-06-30', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('51', 'hp台式机', '台', '10.00', '2', '可以', '2017-06-21', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('52', '台式机', '台', '4569.65', '140', '好电脑', '2018-01-02', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('53', '台式机1', '台', '4569.65', '140', '好电脑', '2018-01-02', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('54', '联想笔记本2', '台', '4500.00', '120', '好', '2018-05-02', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('55', 'IBM服务器2', '台', '1200.00', '100', '不错', '2018-06-14', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('56', 'IBM服务器20182', '个', '2.00', '120', '不错222222', '2018-02-14', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('57', '路由器2', '台', '2.00', '5', '很好2222', '2018-03-02', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('58', '路由器123', '台', '2.00', '5', '很好2222', '2018-03-02', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('59', 'IBM服务器20', '个', '2.00', '140', '不错222222', '2018-02-14', '2018-09-24 11:35:00', null, '0');
INSERT INTO `product` VALUES ('60', '联想笔记本2019', '个', '4600.00', '110', '好2', '2018-09-02', '2018-09-25 23:18:56', null, '0');
INSERT INTO `product` VALUES ('61', '联想笔记本222', '个', '5500.00', '170', '好22', '2018-09-22', '2018-09-25 23:18:56', null, '0');
INSERT INTO `product` VALUES ('62', '宇宙飞船', '个', '4800.58', '140', '不错', '2018-09-26', '2018-09-26 19:55:56', null, '0');
INSERT INTO `product` VALUES ('63', '宇宙飞船22', '个', '5800.58', '240', '不错', '2018-09-27', '2018-09-26 19:55:56', null, '0');
INSERT INTO `product` VALUES ('64', '宇宙飞船33', '台', '5800.58', '340', '不错', '2018-09-28', '2018-09-26 19:55:56', null, '0');
INSERT INTO `product` VALUES ('65', 'a', '个', '120.23', '156', '不错', '2018-09-26', '2018-09-26 19:57:56', null, '0');
INSERT INTO `product` VALUES ('66', 'a', '个', '120.23', '156', '不错', '2018-09-26', '2018-09-26 19:58:12', null, '0');
INSERT INTO `product` VALUES ('67', 'b', '个2', '120.23', '156', '不错', '2018-09-26', '2018-09-26 19:58:12', null, '0');
INSERT INTO `product` VALUES ('68', '宇宙飞船12', '个', '4800.58', '140', '不错', '2018-09-26', '2018-09-26 19:59:48', null, '0');
INSERT INTO `product` VALUES ('69', '宇宙飞船13', '个', '5800.58', '240', '不错', '2018-09-27', '2018-09-26 19:59:48', null, '0');
INSERT INTO `product` VALUES ('70', '宇宙飞船14', '台', '5800.58', '340', '不错', '2018-09-28', '2018-09-26 19:59:48', null, '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `pos_name` varchar(255) DEFAULT NULL COMMENT '岗位',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `profile` varchar(500) DEFAULT NULL COMMENT '个人简介',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'debug', '123456', '程序员2', '23', '15627280967', '一名混迹于一线城市的菜鸟程序员', '2018-09-26 22:05:33', '2018-09-28 15:17:16', '123456@126.com');
INSERT INTO `user` VALUES ('2', 'steadyJack', '654321', '攻城狮', '25', '18316854555', '过得生活有点油腻的IT攻城狮', '2018-09-26 22:06:31', '2018-09-28 15:17:08', '654321@qq.com');
INSERT INTO `user` VALUES ('3', 'sam', '6787767', '架构师', '35', '15627290967', '迈向顶级架构师的屌丝', '2018-09-26 22:06:37', '2018-09-28 15:17:22', '6787767@qq.com');
INSERT INTO `user` VALUES ('5', 'jack3', '123456', '程序员5', '26', '15627280970', '一名混迹于一线城市的菜鸟程序员6', '2018-09-28 10:00:11', '2018-09-28 15:17:30', 'dd@sina.com');
INSERT INTO `user` VALUES ('14', 'lina', '123456', '萌妹子', '23', '15627280970', '萌妹子', '2018-09-28 15:39:05', null, 'SteadyHeart@163.com');
INSERT INTO `user` VALUES ('32', 'mike', '123456', '美男子', '27', '15627280971', '美男子', '2018-09-28 18:38:04', null, 'linsenzhong@126.com');

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) NOT NULL COMMENT '订单编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `is_active` int(11) DEFAULT '1' COMMENT '状态(1=有效；0=无效)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户下单记录表';

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES ('1', 'order_10010', '1', '1', '2018-09-29 22:40:18', null);
INSERT INTO `user_order` VALUES ('2', 'order_10011', '2', '1', '2018-09-29 22:40:34', null);
