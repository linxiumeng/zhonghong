
CREATE TABLE `tb_attest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `cn_name` varchar(255) DEFAULT NULL COMMENT '企业中文名',
  `en_name` varchar(255) DEFAULT NULL COMMENT '英文企业名',
  `area` varchar(255) NOT NULL COMMENT '国家地区',
  `legal_person` varchar(255) DEFAULT NULL COMMENT '企业法人',
  `unified_social_credit_code` varchar(255) DEFAULT NULL COMMENT '统一社会信用代码',
  `telephone` varchar(255) DEFAULT NULL COMMENT '公司固定电话',
  `linkman` varchar(255) DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone_num` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `license_pic1` varchar(255) DEFAULT NULL COMMENT '营业执照照片',
  `license_pic2` varchar(255) DEFAULT NULL COMMENT '企业注册授权委托书',
  `idcard_pic1` varchar(255) DEFAULT NULL COMMENT '法人身份证正面照片',
  `idcard_pic2` varchar(255) DEFAULT NULL COMMENT '法人手持身份证照片',
  `status` varchar(255) DEFAULT NULL COMMENT '认证状态0为初始化，1为认证通过，2为认证失败',
  `type` tinyint(4) DEFAULT '0' COMMENT '认证类型 0-采购商 1-供应商',
  `creat_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

