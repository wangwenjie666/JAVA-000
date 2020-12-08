/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : master

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 08/12/2020 15:32:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order0
-- ----------------------------
DROP TABLE IF EXISTS `t_order0`;
CREATE TABLE `t_order0`  (
  `order_id` bigint(11) NOT NULL,
  `user_id` bigint(11) NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

drop table if exists master.t_order1;
drop table if exists master.t_order2;
drop table if exists master.t_order3;
drop table if exists master.t_order4;
drop table if exists master.t_order5;
drop table if exists master.t_order6;
drop table if exists master.t_order7;
drop table if exists master.t_order8;
drop table if exists master.t_order9;
drop table if exists master.t_order10;
drop table if exists master.t_order11;
drop table if exists master.t_order12;
drop table if exists master.t_order13;
drop table if exists master.t_order14;
drop table if exists master.t_order15;

create table master.t_order1 like master.t_order0;
create table master.t_order2 like master.t_order0;
create table master.t_order3 like master.t_order0;
create table master.t_order4 like master.t_order0;
create table master.t_order5 like master.t_order0;
create table master.t_order6 like master.t_order0;
create table master.t_order7 like master.t_order0;
create table master.t_order8 like master.t_order0;
create table master.t_order9 like master.t_order0;
create table master.t_order10 like master.t_order0;
create table master.t_order11 like master.t_order0;
create table master.t_order12 like master.t_order0;
create table master.t_order13 like master.t_order0;
create table master.t_order14 like master.t_order0;
create table master.t_order15 like master.t_order0;


drop table if exists slave.t_order0;
drop table if exists slave.t_order1;
drop table if exists slave.t_order2;
drop table if exists slave.t_order3;
drop table if exists slave.t_order4;
drop table if exists slave.t_order5;
drop table if exists slave.t_order6;
drop table if exists slave.t_order7;
drop table if exists slave.t_order8;
drop table if exists slave.t_order9;
drop table if exists slave.t_order10;
drop table if exists slave.t_order11;
drop table if exists slave.t_order12;
drop table if exists slave.t_order13;
drop table if exists slave.t_order14;
drop table if exists slave.t_order15;

create table slave.t_order0 like master.t_order0;
create table slave.t_order1 like master.t_order0;
create table slave.t_order2 like master.t_order0;
create table slave.t_order3 like master.t_order0;
create table slave.t_order4 like master.t_order0;
create table slave.t_order5 like master.t_order0;
create table slave.t_order6 like master.t_order0;
create table slave.t_order7 like master.t_order0;
create table slave.t_order8 like master.t_order0;
create table slave.t_order9 like master.t_order0;
create table slave.t_order10 like master.t_order0;
create table slave.t_order11 like master.t_order0;
create table slave.t_order12 like master.t_order0;
create table slave.t_order13 like master.t_order0;
create table slave.t_order14 like master.t_order0;
create table slave.t_order15 like master.t_order0;


