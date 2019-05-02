/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50621
 Source Host           : localhost:3306
 Source Schema         : umbrella

 Target Server Type    : MySQL
 Target Server Version : 50621
 File Encoding         : 65001

 Date: 23/04/2019 22:20:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图片urls',
  `content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `umbrella_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '雨伞编号',
  `umbrella_cabinet_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '雨伞柜编号',
  `lease_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '租赁记录id',
  `period` tinyint(3) NOT NULL DEFAULT 0 COMMENT '投诉期间，0-开锁前，1-开锁中，2-租借中，3-其他',
  `state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态：1-待解决，2-已解决，3-已拒绝',
  `lease_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单类型，current-行程中订单，before-前行程订单',
  `fault_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '问题分类，charge-已计费，not_charge-未计费',
  `solve_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '解决类型，1-结束行程，2-退款，3-拒绝',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租赁反馈表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for global_config
-- ----------------------------
DROP TABLE IF EXISTS `global_config`;
CREATE TABLE `global_config`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '参数名',
  `context` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '配置内容',
  `note` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `created_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `updated_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_global_config_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '全局配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for handle_result
-- ----------------------------
DROP TABLE IF EXISTS `handle_result`;
CREATE TABLE `handle_result`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `work_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '工单id',
  `umbrella_cabinet_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '雨伞柜编号',
  `umbrella_cabinet_coordinate` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '伞柜坐标',
  `umbrella_total_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '伞总量',
  `umbrella_damaged_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '伞破损数量',
  `code_state` tinyint(2) NOT NULL DEFAULT 0 COMMENT '二维码状态',
  `borrow_state` tinyint(2) NOT NULL DEFAULT 0 COMMENT '借伞状态',
  `return_state` tinyint(2) NOT NULL DEFAULT 0 COMMENT '还伞状态',
  `voice_state` tinyint(2) NOT NULL DEFAULT 0 COMMENT '语音状态',
  `position_state` tinyint(2) NOT NULL DEFAULT 0 COMMENT '摆放状态',
  `hardware_state` tinyint(2) NOT NULL DEFAULT 0 COMMENT '硬件状态',
  `handle_state` tinyint(2) NOT NULL DEFAULT 0 COMMENT '详细状态:0-待处理,1-申请无故障,2-申请已修复,3-申请待撤回,4-需再次修复,5-驳回,6-确认无故障,7-确认已修复,8-确认待撤回,9-确认返回客服,10-确认异常',
  `feedback_hardware_value` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '反馈类型',
  `feedback_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '反馈描述',
  `pic` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片',
  `video` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '视频',
  `create_user` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_work_id`(`work_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '处理结果' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for handle_result_log
-- ----------------------------
DROP TABLE IF EXISTS `handle_result_log`;
CREATE TABLE `handle_result_log`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `work_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '工单号id',
  `create_user` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `detail_state` tinyint(4) NOT NULL DEFAULT 0 COMMENT '详细状态:0-待处理,1-申请无故障,2-确认无故障,3-需再次修复,4-申请已修复,5-确认已修复,6-申请待撤回,7-确认待撤回',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_work_id`(`work_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '处理流程流水' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for lease_record
-- ----------------------------
DROP TABLE IF EXISTS `lease_record`;
CREATE TABLE `lease_record`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `lease_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '租赁号',
  `amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '实际支付金额',
  `total_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '租赁总金额',
  `coupon_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠券使用金额',
  `reduce_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '减免金额',
  `uid` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `umbrella_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租赁雨伞编号',
  `cabinet_lend_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '借出雨伞柜编号',
  `cabinet_back_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '归还雨伞柜编号',
  `start_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '开始时间',
  `end_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '结束时间',
  `start_shop_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '开始租赁的投放地编号',
  `start_shop_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '开始租赁的投放地名称',
  `end_shop_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '结束租赁的投放地编号',
  `end_shop_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '结束租赁的投放地名称',
  `lease_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '行程状态：1-未开始，2-行程中，3-已结束，4-租赁失败，5-租赁超时，6-丢失结束，7-超时结束，8-减免结束，9-取消租赁，11-雨伞未注册，12-废弃，13-售出结束',
  `trade_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态：0-待支付, 1-预付中, 3-支付中, 4-已支付, 5-退款中, 6-退款完成',
  `free_deposit` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '信用免押模式 0-正常，1-芝麻信用 2-微信免押',
  `lease_node` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '节点状态',
  `trans_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '流转状态：1-未注册，2-待验证，3-待上线，4-已上线，5-待维修, 6-维修中, 7-报废, 8-已丢失',
  `lease_resource` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'worm' COMMENT '租赁来源，worm表示便利蜂app，wechat表示微信，opApp表示运营app，alipay表示支付宝',
  `entrance` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'pro_umbrella' COMMENT '租赁入口，pro_main表示便利蜂app主扫码，pro_umbrella表示便利蜂app雨伞部分，wechat_scan表示微信主扫码，wechat_umbrella表示微信小程序雨伞部分，op_app表示运营app，alipay表示支付宝',
  `user_resource` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '人员所属，0表示一般用户，1表示公司内部人员(包括未来购,虫极,便利蜂)，2表示外聘人员，3表示其他合作公司人员',
  `start_city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '借出城市',
  `end_city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '还入城市',
  `start_scene` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '借出场景',
  `archived_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '归档金额',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `pay_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'BALANCE' COMMENT '支付方式，balance-余额，wechat-微信',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_lease_number`(`lease_number`) USING BTREE,
  INDEX `idx_uid`(`uid`) USING BTREE,
  INDEX `idx_umbrella_number`(`umbrella_number`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  INDEX `idx_cabinet_lend_number`(`cabinet_lend_number`) USING BTREE,
  INDEX `idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2878 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租赁记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for op_log
-- ----------------------------
DROP TABLE IF EXISTS `op_log`;
CREATE TABLE `op_log`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `log_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '日志类型 1:雨伞 2:雨伞柜 3:用户 4:租赁 5:流水 6:反馈',
  `op_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '操作类型 1:增加 2:修改 3:删除',
  `business_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '业务id 如订单表主键 订单ID',
  `log_context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '具体diff信息',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `note` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注消息',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_business_id`(`business_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for open_record
-- ----------------------------
DROP TABLE IF EXISTS `open_record`;
CREATE TABLE `open_record`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户id',
  `cabinet_lend_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '借出雨伞柜编号',
  `result` tinyint(4) NOT NULL DEFAULT 0 COMMENT '结果 0失败，1成功',
  `msg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '结果信息',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `entrance` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'pro_umbrella' COMMENT '扫码入口，pro_main表示便利蜂app主扫码，pro_umbrella表示便利蜂app雨伞部分，wechat_scan表示微信主扫码，wechat_umbrella表示微信小程序雨伞部分，op_app表示运营app，alipay表示支付宝',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  INDEX `idx_cabinet_lend_number`(`cabinet_lend_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '扫码借伞记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for operation_user
-- ----------------------------
DROP TABLE IF EXISTS `operation_user`;
CREATE TABLE `operation_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '运营人员ID',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '运营人员名称',
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '4QrcOUm6Wau+VuBX8g+IPg==' COMMENT '运营人员密码',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否禁用，0表示禁用，1表示启用',
  `tel` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '运营人员电话号码',
  `sso_user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'SSO用户ID',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `role` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色',
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '负责城市',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_user_id`(`user_id`) USING BTREE,
  UNIQUE INDEX `uniq_tel`(`tel`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `order_snapshot`;
CREATE TABLE `order_snapshot`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `lease_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单号',
  `lend_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '借伞快照内容',
  `return_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '还伞快照内容',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_lease_number`(`lease_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单快照' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for payment_cancel_task
-- ----------------------------
DROP TABLE IF EXISTS `payment_cancel_task`;
CREATE TABLE `payment_cancel_task`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `trade_no` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易号',
  `refund_trade_no` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款交易号',
  `refund_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '退款金额',
  `retry_times` int(11) NOT NULL DEFAULT 0 COMMENT '重试次数',
  `pay_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '支付方式',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付中心取消任务' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for power_manage
-- ----------------------------
DROP TABLE IF EXISTS `power_manage`;
CREATE TABLE `power_manage`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名拼音',
  `province` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '省份',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '城市',
  `title` tinyint(3) NOT NULL DEFAULT 0 COMMENT '职务',
  `leader_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '直属上级',
  `update_operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新操作人',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for send_order
-- ----------------------------
DROP TABLE IF EXISTS `send_order`;
CREATE TABLE `send_order`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `action_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '下发指令操作唯一ID',
  `main_device_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '主设备id',
  `sub_device_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附属设备id',
  `device_type` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '设备类型',
  `operation` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作指令',
  `parameter` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '指令参数',
  `send_type` tinyint(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '下发方式：0-link',
  `send_times` tinyint(2) UNSIGNED NULL DEFAULT 1 COMMENT '第几次下发',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_action_id`(`action_id`) USING BTREE,
  INDEX `idx_device_type_device_id_create_time`(`device_type`, `main_device_id`, `create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '下发指令表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for trade_flow
-- ----------------------------
DROP TABLE IF EXISTS `trade_flow`;
CREATE TABLE `trade_flow`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `trade_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易流水号',
  `amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '实际支付金额',
  `refund_prepay_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '预付款退款金额',
  `refund_part_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '部分退款金额',
  `refund_part_trade_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '部分退款交易流水号',
  `refund_part_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '部分退款时间',
  `refund_prepay_trade_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '预付款退款交易流水号',
  `refund_prepay_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '预付款退款时间',
  `refund_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '退款时间',
  `trade_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '交易时间',
  `state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态：0-待支付, 1-预付中, 3-支付中, 4-已支付, 5-退款中, 6-退款完成, 7-已关闭',
  `lease_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '租赁记录id',
  `uid` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_trade_number`(`trade_number`) USING BTREE,
  INDEX `idx_lease_number`(`lease_number`) USING BTREE,
  INDEX `idx_uid`(`uid`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  INDEX `idx_refund_prepay_trade_number`(`refund_prepay_trade_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '交易流水表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for trade_refund
-- ----------------------------
DROP TABLE IF EXISTS `trade_refund`;
CREATE TABLE `trade_refund`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `lease_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '租赁记录id',
  `trade_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易流水号',
  `refund_trade_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款交易流水号',
  `refund_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '退款金额',
  `refund_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '退款完成时间',
  `state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态：5-退款中, 6-退款完成, 7-已关闭',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_trade_number`(`refund_trade_number`) USING BTREE,
  INDEX `idx_lease_number`(`lease_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '退款记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for umbrella
-- ----------------------------
DROP TABLE IF EXISTS `umbrella`;
CREATE TABLE `umbrella`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `umbrella_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '雨伞编号',
  `umbrella_cabinet_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '雨伞柜编号',
  `trans_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '流转状态：1待投放、2伞柜中、3租借中、4超时售出、5测试取出、6暂存中、7已丢失、8顺序异常、9购买售出',
  `repair_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '维修状态: 0-正常，1-疑似异常，2-待维修，3-维修中，4-已废弃',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `hard_ver` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '硬件版本',
  `soft_ver` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '软件版本',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_umbrella_number`(`umbrella_number`) USING BTREE,
  INDEX `idx_umbrella_cabinet_number`(`umbrella_cabinet_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '雨伞表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for umbrella_cabinet
-- ----------------------------
DROP TABLE IF EXISTS `umbrella_cabinet`;
CREATE TABLE `umbrella_cabinet`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `umbrella_cabinet_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '雨伞柜编号',
  `device_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '设备id',
  `point_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点位ID',
  `msisdn` char(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '物联网卡卡号',
  `vbat` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '电池电压值',
  `csq` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '信号值',
  `shop_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '投放地点编号',
  `shop_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '投放地点名称',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '位置',
  `longitude` decimal(12, 8) NOT NULL DEFAULT 0.00000000 COMMENT '雨伞柜经度',
  `latitude` decimal(12, 8) NOT NULL DEFAULT 0.00000000 COMMENT '雨伞柜纬度',
  `up_lng` decimal(12, 8) NOT NULL DEFAULT 0.00000000 COMMENT '雨伞柜设备上传经度',
  `up_lat` decimal(12, 8) NOT NULL DEFAULT 0.00000000 COMMENT '雨伞柜上传纬度',
  `online_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '在线状态：1-在线，0-离线',
  `max_cap` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '雨伞柜最大容量',
  `property_cap` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '雨伞柜理想容量',
  `trans_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '流转状态：1-未注册，2-待验证，3-待上线，4-已上线，5-待维修, 6-维修中, 7-报废, 8-已丢失',
  `occupy_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '占用状态：0-未占用，1-占用中',
  `merchant_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商户编号',
  `hard_ver` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '硬件版本',
  `soft_ver` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '软件版本',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '城市',
  `upload_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '上传指令时间',
  `locate_time` timestamp(0) NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '上传定位时间',
  `lost_record` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '少记录次数',
  `obtain_record` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '多记录次数',
  `scene` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '场景',
  `put_date` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '投放日期',
  `put_pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '投放后照片',
  `migrate_date` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '迁移日期',
  `position_accordance` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '和签约位置是否一致：0-是，1-否',
  `comment_message` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注消息',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_umbrella_cabinet_number`(`umbrella_cabinet_number`) USING BTREE,
  UNIQUE INDEX `uniq_device_id`(`device_id`) USING BTREE,
  INDEX `idx_city`(`city`) USING BTREE,
  INDEX `idx_shop_name`(`shop_name`) USING BTREE,
  INDEX `idx_scene`(`scene`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '雨伞柜表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for uploadorder
-- ----------------------------
DROP TABLE IF EXISTS `uploadorder`;
CREATE TABLE `uploadorder`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cabinet_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '伞柜id',
  `num` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '雨伞数量',
  `umbrella_id` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '雨伞id',
  `action` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '指令动作',
  `business_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '业务编号',
  `location_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '本地时间',
  `version` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '版本',
  `vbat` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '电池电压值',
  `csq` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '信号值',
  `seq` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '000' COMMENT '错误类型，000-无错误，001-还伞失败，002-借伞失败，003-借伞超时，004-还伞超时，005-还伞未授权',
  `lng` decimal(12, 8) NOT NULL DEFAULT 0.00000000 COMMENT '雨伞柜设备上传经度',
  `lat` decimal(12, 8) NOT NULL DEFAULT 0.00000000 COMMENT '雨伞柜上传纬度',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_cabinet_id_create_time`(`cabinet_id`, `create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '雨伞柜指令上传表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `total_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '总租赁次数',
  `total_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '总租赁时间（分钟）',
  `first_finish_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '首次租赁结束时间',
  `last_finish_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '最后租赁结束时间',
  `foregift_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '押金状态：0-未付，1-付款中, 2-已付',
  `state` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-有效',
  `name` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `tel_number` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for work_order
-- ----------------------------
DROP TABLE IF EXISTS `work_order`;
CREATE TABLE `work_order`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `work_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '工单id',
  `umbrella_cabinet_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '雨伞柜编号',
  `lease_number` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单号',
  `priority` int(3) NOT NULL DEFAULT 0 COMMENT '优先级',
  `feedback_use_value` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '反馈类型',
  `create_user` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `execute_user` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '执行人',
  `feedback_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '反馈描述',
  `pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片',
  `location_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '点位名称',
  `location_position` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '详细位置',
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '伞柜城市',
  `state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态:0-未完成,1-待审核,2-已审核',
  `detail_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '详细状态',
  `review_time` timestamp(3) NOT NULL DEFAULT '1971-01-01 00:00:00.000' COMMENT '审核日期',
  `valid_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '有效状态：0-有效，1-废弃',
  `sync_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '同步客服状态：0-未同步，1-数据异常，2-已同步',
  `comment_message` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常详情',
  `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_work_id`(`work_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工单' ROW_FORMAT = Compact;
