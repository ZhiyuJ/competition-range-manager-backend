/*
Navicat MySQL Data Transfer

Source Server         : jzhy36
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : labs

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-04-29 00:18:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for range
-- ----------------------------
DROP TABLE IF EXISTS `range`;
CREATE TABLE `range` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `range_code` varchar(20) NOT NULL COMMENT '场地编码',
  `range_name` varchar(100) DEFAULT NULL COMMENT '场地名称',
  `range_location` varchar(100) DEFAULT NULL COMMENT '场地地点',
  `status` int(11) DEFAULT NULL COMMENT '1:open;2:close',
  `close_reason` varchar(100) DEFAULT NULL COMMENT '关闭原因',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `range_code` (`range_code`) COMMENT '手填，唯一'
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of range
-- ----------------------------
INSERT INTO `range` VALUES ('15', 'RG2004270011', '篮球场2', '山东大学青岛', '1', null, '这是一条备注', '2020-04-27 18:45:13', '2020-04-27 18:45:13', 'TE001', 'TE001');
INSERT INTO `range` VALUES ('18', 'RG2004270014', '乒乓球场3', '山东大学青岛', '2', '下雨了', '这是一条备注', '2020-04-27 22:38:07', '2020-04-28 23:27:59', 'TE001', '001');
INSERT INTO `range` VALUES ('20', 'RG2004270016', '乒乓球场1', '山东大学威海', '2', '因为下雨了', '这是测试更新接口的备注', '2020-04-27 23:35:03', '2020-04-28 17:17:46', 'TE001', '001');
INSERT INTO `range` VALUES ('21', 'RG2004280001', '羽毛球场', '山东大学青岛', '2', '下雨了', '备注', '2020-04-28 17:16:48', '2020-04-28 17:16:48', 'TE000001', 'TE000001');
INSERT INTO `range` VALUES ('29', 'RG2004280009', '排球场', '山东大学青岛', '1', null, '备注', '2020-04-28 23:28:59', '2020-04-28 23:28:59', 'TE000001', 'TE000001');
