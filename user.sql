/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.37
Source Server Version : 50731
Source Host           : 192.168.3.37:3306
Source Database       : czq_demo

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-09-10 17:42:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  `remark` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('35', '陈志强', 'qwe123', '0', '0');
INSERT INTO `user` VALUES ('113', 'admin', 'qwe123', '0', '1');
INSERT INTO `user` VALUES ('126', '路飞', 'qwe123', '0', '1');
INSERT INTO `user` VALUES ('127', '索隆', 'qwe123', '0', '0');
