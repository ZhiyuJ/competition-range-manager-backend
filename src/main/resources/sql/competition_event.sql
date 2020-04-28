/*
Navicat MySQL Data Transfer

Source Server         : jzhy36
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : labs

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-04-29 00:20:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for competition_event
-- ----------------------------
DROP TABLE IF EXISTS `competition_event`;
CREATE TABLE `competition_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `competition_event_code` varchar(20) NOT NULL COMMENT '比赛项目编码',
  `competition_event_name` varchar(20) DEFAULT NULL COMMENT '比赛项目名称',
  `suite_type` int(11) DEFAULT NULL COMMENT '1：成年组；2：青少年组；2：老年组',
  `range_code` varchar(20) DEFAULT NULL COMMENT '场地编码',
  `plan_start_at` date DEFAULT NULL COMMENT '计划开始日期',
  `plan_end_at` date DEFAULT NULL COMMENT '计划结束日期',
  `status` int(11) DEFAULT NULL COMMENT '1:未开始;2:进行中;3:已结束',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `competition_event_code` (`competition_event_code`) USING BTREE COMMENT '自动生成，全局唯一'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competition_event
-- ----------------------------
INSERT INTO `competition_event` VALUES ('1', '123', '乒乓比赛', '1', 'RG2004260003', '2020-05-01', '2020-05-01', '3', '2020-04-27 16:10:04', '2020-04-27 16:11:17', 'root', 'root');
