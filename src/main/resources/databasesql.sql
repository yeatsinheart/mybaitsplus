CREATE DATABASE `max` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `game_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` varchar(45) DEFAULT NULL,
  `merchant_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='游戏渠道';

CREATE TABLE `game_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='游戏项目';

CREATE TABLE `game_merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `merchant_code` varchar(45) DEFAULT NULL,
  `public_key` varchar(45) DEFAULT NULL,
  `private_key` varchar(45) DEFAULT NULL,
  `gate` varchar(45) DEFAULT NULL COMMENT '网关',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='游戏商户';

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL COMMENT '标题',
  `content` varchar(45) DEFAULT NULL COMMENT '内容',
  `content_type` int(11) DEFAULT '0' COMMENT '内容类型 0 普通文本',
  `type` int(11) DEFAULT NULL COMMENT '消息类型 0 公告 1 消息',
  `receive_user_id` int(11) DEFAULT NULL COMMENT '接收用户ID',
  `readed` int(11) DEFAULT NULL COMMENT '是否已读',
  `jump` varchar(45) DEFAULT NULL COMMENT '跳转内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统消息表';

CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付方式ID',
  `name` varchar(45) DEFAULT NULL COMMENT '支付方式',
  `status` int(11) DEFAULT '0' COMMENT '开启状态 0开启 1关闭',
  `code` varchar(45) NOT NULL COMMENT '支付方式代码',
  `order` int(11) DEFAULT '0' COMMENT '展示顺序 值大排前',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付方式';

CREATE TABLE `payment_bank_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '银行卡使用记录',
  `user_id` varchar(45) DEFAULT NULL,
  `bank` varchar(45) DEFAULT NULL,
  `card` varchar(45) DEFAULT NULL COMMENT '银行卡账号',
  `name` varchar(45) DEFAULT NULL COMMENT '持卡人姓名',
  `create_time` varchar(45) DEFAULT NULL COMMENT '绑定时间',
  `ip` varchar(45) DEFAULT NULL COMMENT '绑定IP',
  PRIMARY KEY (`id`),
  KEY `unique_bank_card` (`bank`,`card`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='充值银行卡使用记录';

CREATE TABLE `payment_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '渠道ID',
  `payment_id` int(11) DEFAULT NULL COMMENT '支付方式ID',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商户ID',
  `name` varchar(45) DEFAULT NULL COMMENT '通道名称',
  `order` int(11) DEFAULT NULL COMMENT '排序',
  `min` bigint(20) DEFAULT NULL COMMENT '最小充值金额',
  `max` bigint(20) DEFAULT NULL COMMENT '最大充值金额',
  `pay_url` varchar(45) DEFAULT NULL COMMENT '支付地址',
  `result_url` varchar(45) DEFAULT NULL COMMENT '结果跳转地址',
  `notify_url` varchar(45) DEFAULT NULL COMMENT '通知地址',
  `daily_limit` bigint(20) DEFAULT NULL COMMENT '每日收款上限',
  `status` int(11) DEFAULT NULL COMMENT '状态 0 开启 1关闭',
  `need_bank` int(11) DEFAULT '0' COMMENT '是否需要银行卡信息 0不需要 1需要',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付方式关联的支付渠道设置';

CREATE TABLE `payment_merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '名称',
  `merchant_no` varchar(45) DEFAULT NULL COMMENT '商户号',
  `public_key` varchar(45) DEFAULT NULL COMMENT '对方提供的公钥',
  `private_key` varchar(45) DEFAULT NULL COMMENT '自己的私钥',
  `gate` varchar(45) DEFAULT NULL COMMENT '网关地址',
  `status` varchar(45) DEFAULT NULL COMMENT '状态 0 开启 1 关闭',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='接入的支付商户';

CREATE TABLE `payment_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` varchar(45) DEFAULT NULL COMMENT '支付渠道',
  `money` varchar(45) DEFAULT NULL COMMENT '金额',
  `no` varchar(45) DEFAULT NULL COMMENT '订单号',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '订单提交时间',
  `user_id` varchar(45) DEFAULT NULL COMMENT '充值人ID',
  `status` varchar(45) DEFAULT '0' COMMENT '状态：0 用户发起充值  。已成功状态 1 三方异步通知支付成功 -》 2  人工审核到账成功  3 充值已到用户钱包',
  `notify_time` varchar(45) DEFAULT NULL COMMENT '三方修改时间 ',
  `check_time` varchar(45) DEFAULT NULL COMMENT '人工审核时间',
  `bank_detail` varchar(45) DEFAULT NULL COMMENT '付款人信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='充值订单';

CREATE TABLE `payment_order_bank_detail` (
  `bank_id` varchar(45) DEFAULT NULL COMMENT '支持的银行卡ID',
  `card` varchar(45) DEFAULT NULL COMMENT '银行卡账号',
  `name` varchar(45) DEFAULT NULL COMMENT '付款人姓名',
  KEY `unique_card` (`bank_id`,`card`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单付款人信息';

CREATE TABLE `payment_support_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank` varchar(45) DEFAULT NULL COMMENT '银行',
  `ico` varchar(45) DEFAULT NULL COMMENT '图标',
  `status` varchar(45) DEFAULT '0' COMMENT '状态0 支持  1 不支持',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付支持银行卡';

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `passwd` varchar(45) DEFAULT NULL,
  `nick` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

CREATE TABLE `user_action` (
  `id` int(11) NOT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `action` varchar(45) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `device` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户行为';

CREATE TABLE `user_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `bank` varchar(45) DEFAULT NULL COMMENT '银行',
  `card` varchar(45) DEFAULT NULL COMMENT '银行卡号',
  `name` varchar(45) DEFAULT NULL COMMENT '持卡人姓名',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户银行卡表';

