DROP TABLE IF EXISTS `registercenterInterface`;
CREATE TABLE `registercenterInterface` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `interfacename` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Interface',
  `version` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '版本',
  `dgroup` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '分组',
    `projectid` bigint(20) unsigned DEFAULT '1' COMMENT '项目id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='registercenterInterface';

DROP TABLE IF EXISTS `registercenterMethods`;
CREATE TABLE `registercenterMethods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `interfaceid` bigint(20) unsigned NOT NULL COMMENT 'InterfaceId',
  `methodname` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '方法名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='registercenterMethods表';

ALTER TABLE deployunit_model add COLUMN `projectid` bigint(20) unsigned DEFAULT '1' COMMENT '项目id' ;
 ALTER TABLE deployunit_model add COLUMN `servicetype` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'Http服务' COMMENT '服务类型';