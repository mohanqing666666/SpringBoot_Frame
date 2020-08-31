/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : db_springboot_teach_01

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-09-29 22:48:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '产品名',
  `product_no` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='产品信息表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '玩具娃娃', 'product_10010', '2018-09-16 20:00:00', null);
INSERT INTO `product` VALUES ('2', '变形金刚', 'product_10011', '2018-09-16 20:00:19', null);
INSERT INTO `product` VALUES ('3', '大黄蜂', 'product_10012', '2018-09-16 20:00:29', null);
INSERT INTO `product` VALUES ('4', '灭霸', 'product_10013', '2018-09-16 20:41:21', null);
INSERT INTO `product` VALUES ('5', '灭霸v2', 'product_10019', '2018-09-17 22:08:15', null);
