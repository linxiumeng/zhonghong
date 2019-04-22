
CREATE TABLE `tb_credit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `loan_application` text COMMENT '借款申请表',
  `financial_statement` varchar(255) DEFAULT NULL COMMENT '财务报表',
  `tax_certification` varchar(255) DEFAULT NULL COMMENT '缴税证明',
  `account_statement` varchar(255) DEFAULT NULL COMMENT '银行流水',
  `certificate_of_credit_line` varchar(255) DEFAULT NULL COMMENT '授信额度证明',
  `orther` varchar(255) DEFAULT NULL COMMENT '其他证明',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

