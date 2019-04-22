
CREATE TABLE `tb_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `editor` varchar(255) NOT NULL COMMENT '编辑人',
  `details` text COMMENT '内容',
  `type` tinyint(4) DEFAULT '0' COMMENT '类型。0-国内，1-国际',
  `tab` varchar(255) DEFAULT NULL COMMENT '标签',
  `views` int(255) DEFAULT '0' COMMENT '阅读数',
  `origin` varchar(255) DEFAULT NULL COMMENT '新闻来源',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=473 DEFAULT CHARSET=utf8;

