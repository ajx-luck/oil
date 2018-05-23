/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : fat

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-25 12:21:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for path_to_wechat
-- ----------------------------
DROP TABLE IF EXISTS `path_to_wechat`;
CREATE TABLE `path_to_wechat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) NOT NULL,
  `price` bigint(20) NOT NULL,
  `url_path` varchar(255) NOT NULL,
  `wechat_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of path_to_wechat
-- ----------------------------
INSERT INTO `path_to_wechat` VALUES ('1', '1000', '129', 'fxc', 'jbjx6572');
SET FOREIGN_KEY_CHECKS=1;
