
CREATE TABLE `tb_purchase_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL COMMENT '买方id',
  `buyer_company` varchar(255) DEFAULT NULL COMMENT '买方公司',
  `buyer_linkman` varchar(255) DEFAULT NULL COMMENT '买方联系人',
  `buyer_phone` varchar(255)  DEFAULT NULL COMMENT '买方电话',
  `buyer_email` varchar(255)  DEFAULT NULL COMMENT '买方邮箱',
  `provider_id` int(11) DEFAULT NULL COMMENT '卖方id',
  `provider_company` varchar(255) DEFAULT NULL COMMENT '卖方公司',
  `provider_linkman` varchar(255) DEFAULT NULL COMMENT '卖方联系人',
  `provider_phone` varchar(255)  DEFAULT NULL COMMENT '卖方电话',
  `provider_email` varchar(255)  DEFAULT NULL COMMENT '卖方邮箱',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
  `goods_pic` varchar(255)  DEFAULT NULL COMMENT '商品图片',
  `quotation_id` int(11) DEFAULT NULL COMMENT '报价单id',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `goods_type` varchar(255) DEFAULT NULL COMMENT '商品类型',
  `trade_clause` varchar(255) DEFAULT NULL COMMENT '贸易条款',
  `payment_by` varchar(255)  DEFAULT NULL COMMENT '信用方式',
  `goods_unit` varchar(255) DEFAULT NULL COMMENT '商品单位',
  `goods_price` varchar(255)  DEFAULT NULL COMMENT '商品单价',
  `final_quotation` varchar(255)  DEFAULT NULL COMMENT '最终报价，浮动价格确认后填入',
  `pricing_manner` varchar(255)  DEFAULT NULL COMMENT '计价基准',
  `contractual_value_date` varchar(255) DEFAULT NULL COMMENT '计价日期',
  `premiums_discounts` varchar(255)  DEFAULT NULL COMMENT '升贴水',
  `pay_day` varchar(255)  DEFAULT NULL COMMENT '支付日',
  `buyer_remark` varchar(255) DEFAULT NULL COMMENT '买方备注',
  `place_of_origin` varchar(255)  DEFAULT NULL COMMENT '油产地',
  `oil_type` varchar(255)  DEFAULT NULL COMMENT '油种类',
  `api` varchar(255)  DEFAULT NULL COMMENT 'api度',
  `sulphur_content` varchar(255)  DEFAULT NULL COMMENT '含硫量%',
  `acid_value` varchar(255)  DEFAULT NULL COMMENT '酸值mgkoh/g',
  `carbon_residue` varchar(255)  DEFAULT NULL COMMENT '残炭%',
  `nickel` varchar(255)  DEFAULT NULL COMMENT '镍ppm',
  `vanadium` varchar(255)  DEFAULT NULL COMMENT '钒ppm',
  `shrink` varchar(255)  DEFAULT NULL COMMENT '>350摄氏度质量收缩率（%）',
  `status` int(4) DEFAULT '0' COMMENT '采购单状态0为初始化',
  `goods_amount` int(255) DEFAULT '0' COMMENT '商品数量',
  `ship_address` varchar(255)  DEFAULT NULL COMMENT '发货地址',
  `load_date` varchar(255)  DEFAULT NULL COMMENT '装货日期',
  `creat_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `goods_remark` varchar(255) DEFAULT NULL COMMENT '商品備注',
  `buyer_address` varchar(255)  DEFAULT NULL COMMENT '买房地址',
  `contract` varchar(255) DEFAULT NULL COMMENT '合同',
  `refuse_cause` varchar(255) DEFAULT NULL COMMENT '订单打回原因',
  `settle_bill` varchar(255) DEFAULT NULL COMMENT '结算单据',
  `file_point` varchar(255) DEFAULT NULL COMMENT '原油指标文件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;
