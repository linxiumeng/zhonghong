CREATE TABLE `tb_account_repayment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `total_amount` decimal(20, 5) DEFAULT NULL COMMENT '总金额',
  `wait_amount` decimal(20, 5) DEFAULT NULL COMMENT '未还金额',
  `paid_amount` decimal(20, 5) DEFAULT NULL COMMENT '已还金额',
  `current_period` int(11) DEFAULT NULL COMMENT '当前期数',
  `total_interest` decimal(10, 5) DEFAULT NULL COMMENT '总利息',
  `wait_interest` decimal(10, 5) DEFAULT NULL COMMENT '待付利息',
  `paid_interest` decimal(10, 5) DEFAULT NULL COMMENT '已支付利息',
  `periods` int(11) DEFAULT NULL COMMENT '总期数',
  `recent_repayment_date` datetime(0) DEFAULT NULL COMMENT '最近还款日期',
  `create_date` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB ;
