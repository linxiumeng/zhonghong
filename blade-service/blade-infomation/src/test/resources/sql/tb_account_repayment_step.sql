
CREATE TABLE `tb_account_repayment_step`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `repayment_id` bigint(20) DEFAULT NULL,
  `account` decimal(10, 5) DEFAULT NULL COMMENT '金额',
  `interest` decimal(10, 5) DEFAULT NULL COMMENT '利息',
  `paid_account` decimal(10, 5) DEFAULT NULL COMMENT '已还金额',
  `paid_interest` decimal(10, 5) DEFAULT NULL COMMENT '已还利息',
  `status` tinyint(4) DEFAULT 0 COMMENT '状态 0:未还款 1:已还款 2:已逾期',
  `preiod` int(11) DEFAULT NULL COMMENT '期数',
  `preiod_date` datetime(0) DEFAULT NULL COMMENT '还款日期',
  `create_date` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

