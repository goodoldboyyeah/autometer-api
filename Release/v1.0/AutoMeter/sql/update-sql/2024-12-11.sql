DROP TABLE IF EXISTS `dubbomodelmethods`;
CREATE TABLE `dubbomodelmethods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `modelid` bigint(20) unsigned NOT NULL COMMENT 'DeployUnitId',
  `methodname` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '方法名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='dubbomodelmethods表';