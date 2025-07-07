INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('服务类型', 'servicetype', '服务类型', 'Http服务', '2024-10-12 14:45:58', '2024-10-12 14:45:58');
INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('服务类型', 'servicetype', '服务类型', 'Dubbo服务', '2024-10-12 14:46:15', '2024-10-12 14:46:15');

INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('环境部署内容', 'machinedeploy', '数据库', 'zookeeper', '2024-11-01 19:42:50', '2024-11-01 19:42:50');

INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', '整型', 'int', '2024-10-27 15:04:07', '2024-10-27 15:04:07');
INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', '长整型', 'long', '2024-10-27 15:04:33', '2024-10-27 15:04:33');
INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', '字符串', 'String', '2024-10-27 15:04:33', '2024-10-27 15:04:33');

INSERT INTO testcenter.dictionary
( dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', 'char', 'char', '2024-11-07 20:03:13', '2024-11-07 20:03:13');
INSERT INTO testcenter.dictionary
( dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', 'short', 'short', '2024-11-07 20:03:44', '2024-11-07 20:03:44');
INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', 'float', 'float', '2024-11-07 20:04:32', '2024-11-07 20:04:32');
INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', 'double', 'double', '2024-11-07 20:05:00', '2024-11-07 20:05:00');
INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', 'boolean', 'boolean', '2024-11-07 20:05:34', '2024-11-07 20:05:34');
INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', 'byte', 'byte', '2024-11-07 20:06:10', '2024-11-07 20:06:10');
INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('Dubbo参数类型', 'DubboParamsType', 'Object', 'Object', '2024-11-07 20:06:55', '2024-11-07 20:06:55');



 ALTER TABLE apicases add COLUMN `servicetype` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'Http服务' COMMENT '服务类型';
 ALTER TABLE apicases add COLUMN `protocal` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'http' COMMENT '服务类型';
 ALTER TABLE deployunit add COLUMN `servicetype` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'Http服务' COMMENT '服务类型';
 ALTER TABLE `dispatch` add COLUMN `servicetype` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'Http服务' COMMENT '服务类型';
 ALTER TABLE dispatch_casedata add COLUMN `dispatchid` bigint(20) unsigned NOT NULL COMMENT '调度id' ;
 ALTER TABLE apicases_report add COLUMN `servicetype` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'Http服务' COMMENT '服务类型';
 ALTER TABLE condition_api add COLUMN `servicetype` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'Http服务' COMMENT '服务类型';
 ALTER TABLE testscene_testcase add COLUMN `servicetype` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'Http服务' COMMENT '服务类型';
 ALTER TABLE apicases_report add COLUMN `apiname` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '接口名';



DROP TABLE IF EXISTS `dubboapi`;
CREATE TABLE `dubboapi` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `deployunitid` bigint(20) unsigned NOT NULL COMMENT 'DeployUnitId',
  `deployunitname` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '发布单元名',
  `apiname` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '接口名',
  `version` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '版本',
  `responecontenttype` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '响应数据格式，基础类型，对象',
  `memo` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上一次修改时间',
  `creator` varchar(128) DEFAULT NULL COMMENT '创建者',
  `projectid` bigint(20) unsigned DEFAULT '1' COMMENT '项目id',
  `casecounts` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用例数量',
  `modelname` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '模块名',
  `modelid` bigint(20) unsigned DEFAULT '0' COMMENT '模块id',
  `mnickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '维护者',
  `mid` bigint(20) unsigned NOT NULL COMMENT '维护者id',
  `creatorid` bigint(20) unsigned NOT NULL COMMENT '维护者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='dubboapi表';


INSERT INTO permission(id, resource, code, handle) VALUES(311, 'dubbo接口', 'dubboapi:delete', '删除');
INSERT INTO permission(id, resource, code, handle) VALUES(312, 'dubbo接口', 'dubboapi:list', '列表');
INSERT INTO permission(id, resource, code, handle) VALUES(313, 'dubbo接口', 'dubboapi:add', '添加');
INSERT INTO permission(id, resource, code, handle) VALUES(314, 'dubbo接口', 'dubboapi:search', '查询');
INSERT INTO permission(id, resource, code, handle) VALUES(315, 'dubbo接口', 'dubboapi:detail', '详情');
INSERT INTO permission(id, resource, code, handle) VALUES(316, 'dubbo接口', 'dubboapi:update', '修改');


DROP TABLE IF EXISTS `dubboapi_params`;
CREATE TABLE `dubboapi_params` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `apiid` bigint(20) unsigned NOT NULL COMMENT 'apiId',
  `apiname` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'api名',
  `deployunitid` bigint(20) unsigned NOT NULL COMMENT '发布单元Id',
  `deployunitname` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '发布单元名',
  `keyname` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'key名',
  `paramstype` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin  DEFAULT NULL COMMENT '参数类型',
  `keydefaultvalue` text COMMENT 'Key默认值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上一次修改时间',
  `creator` varchar(128) DEFAULT NULL COMMENT '创建者',
  `creatorid` bigint(20) unsigned NOT NULL COMMENT '维护者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='dubboapi参数表';


DROP TABLE IF EXISTS `dubboapi_casedata`;
CREATE TABLE `dubboapi_casedata` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `caseid` bigint(20) unsigned NOT NULL COMMENT '用例Id',
  `casename` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用例名',
  `apiparam` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'api参数',
  `apiparamvalue` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '用例参数值',
  `memo` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上一次修改时间',
  `paramstype` varchar(20) DEFAULT NULL COMMENT '参数类型',
  `mid` bigint(20) unsigned NOT NULL COMMENT '维护者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='dubboapi用例数据表';

DROP TABLE IF EXISTS `dispatch_dubboapicasedata`;
CREATE TABLE `dispatch_dubboapicasedata` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `dispatchid` bigint(20) unsigned NOT NULL COMMENT '调度id',
  `caseid` bigint(20) unsigned NOT NULL COMMENT '用例Id',
  `casename` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用例名',
  `apiparam` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'api参数',
  `apiparamvalue` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '用例参数值',
  `memo` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上一次修改时间',
  `paramstype` varchar(20) DEFAULT NULL COMMENT '参数类型',
  `mid` bigint(20) unsigned NOT NULL COMMENT '维护者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='dubbo调度用例数据表';