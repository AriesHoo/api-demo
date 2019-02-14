/*
Navicat MySQL Data Transfer

Source Server         : Test
Source Server Version : 50643
Source Host           : localhost:3306
Source Database       : ah_test

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-02-14 10:34:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(64) NOT NULL COMMENT '用户ID',
  `user_name` varchar(64) NOT NULL COMMENT '用户名-账号可做登录使用',
  `nick_name` varchar(64) DEFAULT '' COMMENT '用户昵称',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT '' COMMENT '电话号码',
  `we_chat_open_id` varchar(64) DEFAULT '' COMMENT '微信授权id',
  `qq_open_id` varchar(64) DEFAULT '' COMMENT 'qq授权id',
  `head_url` varchar(256) DEFAULT '' COMMENT '头像地址',
  `signature` varchar(128) DEFAULT '' COMMENT '个性签名',
  `create_time` datetime DEFAULT '2018-01-01 00:00:01' COMMENT '创建时间',
  `login_time` datetime DEFAULT '2018-01-01 00:00:01' COMMENT '上次登录时间',
  `modify_time` datetime DEFAULT '2018-01-01 00:00:01' COMMENT '修改时间',
  PRIMARY KEY (`id`,`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
