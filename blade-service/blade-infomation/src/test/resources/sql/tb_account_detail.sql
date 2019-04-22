
CREATE TABLE `tb_account_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `total` decimal(20, 5) DEFAULT NULL COMMENT '总资产',
  `account` decimal(20, 5) DEFAULT NULL COMMENT '使用金额',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_date` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB ;

