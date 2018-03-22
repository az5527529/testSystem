CREATE TABLE `user_info` (
  `user_info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名字',
  `telephone` varchar(16) NOT NULL DEFAULT '' COMMENT '电话号码',
  `score` float NOT NULL DEFAULT 0 COMMENT '分数',
  `openid` varchar(64) NOT NULL DEFAULT '' COMMENT '微信id',
  `test_time` varchar(20) NOT NULL DEFAULT '' COMMENT '作答时间',
  
  PRIMARY KEY (`user_info_id`),
  key user_info_user_name(`user_name`),
  key user_info_openid(`openid`),
  key user_info_test_time(`test_time`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
