
CREATE TABLE `tb_banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id自增',
  `logo` varchar(256) DEFAULT NULL COMMENT 'logo',
  `path` varchar(256) DEFAULT NULL COMMENT 'banner路径',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_open` tinyint(4) DEFAULT NULL COMMENT '是否展示 0-隐藏，1-展示',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ;

