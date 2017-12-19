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
INSERT INTO `path_to_wechat` VALUES ('1', '1000', '129', 'fxa', 'lzd7411');
INSERT INTO `path_to_wechat` VALUES ('2', '1000', '129', 'fxa', 'RV8425');
INSERT INTO `path_to_wechat` VALUES ('3', '1000', '129', 'fxa', 'bv1701');
INSERT INTO `path_to_wechat` VALUES ('4', '1000', '129', 'fxa', 'tv1727');
INSERT INTO `path_to_wechat` VALUES ('5', '1000', '129', 'fxa', 'xv7958');
INSERT INTO `path_to_wechat` VALUES ('6', '1000', '129', 'fxa', 'fk64467');
INSERT INTO `path_to_wechat` VALUES ('7', '1000', '129', 'fxa', 'fk29135');
INSERT INTO `path_to_wechat` VALUES ('8', '1000', '129', 'fxb', 'FK040591');
INSERT INTO `path_to_wechat` VALUES ('9', '1000', '129', 'fxb', 'FK40579');
INSERT INTO `path_to_wechat` VALUES ('10', '1000', '129', 'fxb', 'fk319204');
INSERT INTO `path_to_wechat` VALUES ('11', '1000', '129', 'fxb', 'fk64067');
INSERT INTO `path_to_wechat` VALUES ('12', '1000', '129', 'fxb', 'fk58751');
INSERT INTO `path_to_wechat` VALUES ('13', '1000', '129', 'fxb', 'fk29436');
INSERT INTO `path_to_wechat` VALUES ('14', '1000', '129', 'fxb', 'fk71866');
INSERT INTO `path_to_wechat` VALUES ('15', '1000', '129', 'fxb', 'fk44938');
INSERT INTO `path_to_wechat` VALUES ('16', '1000', '129', 'fxc', 'cph131466');
INSERT INTO `path_to_wechat` VALUES ('17', '1000', '129', 'fxc', 'xf409926');
