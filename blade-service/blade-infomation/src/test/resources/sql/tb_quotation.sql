
CREATE TABLE `tb_quotation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `demand_id` int(11) DEFAULT NULL COMMENT '关联需求单id',
  `user_id` int(11) DEFAULT NULL COMMENT '报价用户id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价，0为浮动价',
  `f_type` varchar(255) DEFAULT NULL COMMENT '产品类型',
  `f_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `f_unit` varchar(255) DEFAULT NULL COMMENT '产品单位',
  `num` int(11) DEFAULT NULL COMMENT '交易数量',
  `trade_clause` varchar(255) DEFAULT NULL COMMENT '贸易条款',
  `payment_by` varchar(255) DEFAULT NULL COMMENT '信用方式',
  `pricing_manner` varchar(255) DEFAULT NULL COMMENT '计价基准',
  `contractual_value_date` varchar(255) DEFAULT NULL COMMENT '计价日期',
  `premiums_discounts` varchar(255) DEFAULT NULL COMMENT '升贴水',
  `pay_day` varchar(255) DEFAULT NULL COMMENT '支付日',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `place_of_origin` varchar(255) DEFAULT NULL COMMENT '油产地',
  `oil_type` varchar(255) DEFAULT NULL COMMENT '油种类',
  `api` varchar(255) DEFAULT NULL COMMENT 'api度',
  `sulphur_content` varchar(255) DEFAULT NULL COMMENT '含硫量%',
  `acid_value` varchar(255) DEFAULT NULL COMMENT '酸值mgkoh/g',
  `carbon_residue` varchar(255) DEFAULT NULL COMMENT '残炭%',
  `nickel` varchar(255) DEFAULT NULL COMMENT '镍ppm',
  `vanadium` varchar(255) DEFAULT NULL COMMENT '钒ppm',
  `shrink` varchar(255) DEFAULT NULL COMMENT '>350摄氏度质量收缩率（%）',
  `status` varchar(255) DEFAULT NULL COMMENT '报价单状态0为初始',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ship_address` varchar(255) DEFAULT NULL COMMENT '提貨地址',
  `load_date` varchar(255) DEFAULT NULL COMMENT '裝在時間',
  `other_desc` varchar(255) DEFAULT NULL COMMENT '其他説明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

