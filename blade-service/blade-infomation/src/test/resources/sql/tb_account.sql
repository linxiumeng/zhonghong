
CREATE TABLE `tb_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `account` decimal(20,5) DEFAULT NULL COMMENT '可用余额',
  `total` decimal(20,5) DEFAULT NULL COMMENT '账户总资产',
  `freeze_amount` decimal(20,5) DEFAULT NULL COMMENT '冻结金额',
  `cash_fund` decimal(20,5) DEFAULT NULL COMMENT '保证金',
  `credit_limit` varchar(255) DEFAULT NULL COMMENT '审批额度',
  `credit_unit` varchar(255) DEFAULT NULL COMMENT '单笔可用额度',
  `credit_high` varchar(255) DEFAULT NULL COMMENT '最高可用额度',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `credit_level` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

