ALTER TABLE dubboapi add COLUMN `dgroup` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT null COMMENT '分组';

INSERT INTO testcenter.dictionary
(dicname, diccode, dicitemname, dicitmevalue, create_time, lastmodify_time)
VALUES('环境部署内容', 'machinedeploy', '数据库', 'nacos', '2024-11-01 19:42:50', '2024-11-01 19:42:50');