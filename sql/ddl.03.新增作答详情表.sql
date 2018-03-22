CREATE TABLE `answer_detail` (
  `answer_detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `question_id` bigint(20) NOT NULL default 0 COMMENT '问题id',
  `question_type` tinyint NOT NULL default 0 COMMENT '问题类型',
  `user_answer` varchar(8) NOT NULL DEFAULT '' COMMENT '用户答案',
  `result` tinyint NOT NULL DEFAULT 0 COMMENT '结果',
  `user_info_id` bigint NOT NULL DEFAULT 0 COMMENT '用户id',
  
  PRIMARY KEY (`answer_detail_id`),
  key answer_detail_question_id(`question_id`),
  key answer_detail_user_info_id(`user_info_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
