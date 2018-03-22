CREATE TABLE `user_img` (
  `user_img_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL default '' COMMENT '姓名',
  `telephone` varchar(16) NOT NULL default '' COMMENT '电话',
  `img_url` varchar(128) NOT NULL default '' COMMENT '图片地址',
  `img_name` varchar(64) NOT NULL default '' COMMENT '图片名称',
  `openid` varchar(64) NOT NULL default '' COMMENT '微信id',  
  `upload_time` varchar(64) NOT NULL default '' COMMENT '上传时间',  
  
  PRIMARY KEY (`user_img_id`),
  key user_img_user_name(`user_name`),
  key user_img_telephone(`telephone`),
  key user_img_openid(`openid`),
  key user_img_upload_time(`upload_time`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
