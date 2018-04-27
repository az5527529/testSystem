CREATE TABLE `activity` (
  `activity_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_name` varchar(64) NOT NULL default '' COMMENT '活动名称',
  `content` varchar(256) NOT NULL default '' COMMENT '活动内容',
  `start_time` varchar(20) NOT NULL DEFAULT '' COMMENT '开始时间',
  `end_time` varchar(20) NOT NULL DEFAULT '' COMMENT '结束时间',
  `background_url` varchar(128) NOT NULL DEFAULT '' COMMENT '背景图片url',
  `is_active` tinyint NOT NULL DEFAULT 0 COMMENT '是否启用',
  `activity_type` tinyint NOT NULL DEFAULT 0 COMMENT '活动类型',
  `created_user` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  
  PRIMARY KEY (`activity_id`),
  key activity_start_time(`start_time`),
  key activity_end_time(`end_time`),
  key activity_created_user(`created_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
