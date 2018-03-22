CREATE TABLE `login_user` (
  `login_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  
  PRIMARY KEY (`login_user_id`),
  key login_user_user_name(`user_name`),
  key login_user_password(`password`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
