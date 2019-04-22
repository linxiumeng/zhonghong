
CREATE TABLE `tb_account_recharge`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `account` decimal(10, 5) DEFAULT NULL COMMENT '充值金额',
  `type` tinyint(4) DEFAULT 0 COMMENT '充值类型0是充值，1是提现',
  `status` tinyint(4) DEFAULT 0 COMMENT '状态',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `verifier` varchar(255) DEFAULT NULL COMMENT '审核人',
  `proof` varchar(255)  DEFAULT NULL COMMENT '审核凭据',
  `verifiy_date` datetime(0) DEFAULT NULL COMMENT '验证时间',
  `create_date` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

