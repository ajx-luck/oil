/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : fat

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-11-30 17:07:14
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of path_to_wechat
-- ----------------------------
INSERT INTO `path_to_wechat` VALUES ('1', '1000', '129', 'fx1', 'fx96756');
INSERT INTO `path_to_wechat` VALUES ('2', '1000', '129', 'fx2', 'fx95687');
INSERT INTO `path_to_wechat` VALUES ('3', '1000', '129', 'fx3', 'cph33566');
INSERT INTO `path_to_wechat` VALUES ('4', '1000', '129', 'fx4', 'cph33566');
INSERT INTO `path_to_wechat` VALUES ('5', '1000', '129', 'fx5', 'cph33566');
INSERT INTO `path_to_wechat` VALUES ('6', '1000', '129', 'fx6', 'cph33566');
INSERT INTO `path_to_wechat` VALUES ('7', '1000', '129', 'fx7', 'cph33566');
INSERT INTO `path_to_wechat` VALUES ('8', '1000', '129', 'fx8', 'cph33566');
INSERT INTO `path_to_wechat` VALUES ('9', '1000', '129', 'fx9', 'cph33566');
INSERT INTO `path_to_wechat` VALUES ('10', '1000', '129', 'fx10', 'cph33566');
