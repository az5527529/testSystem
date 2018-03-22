CREATE TABLE `choice_question` (
  `choice_question_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(256) NOT NULL default '' COMMENT '问题内容',
  `options` varchar(512) NOT NULL DEFAULT '' COMMENT '选项',
  `answer` varchar(8) NOT NULL DEFAULT '' COMMENT '答案',
  `question_no` varchar(8) NOT NULL DEFAULT '' COMMENT '题目编号',
  
  PRIMARY KEY (`choice_question_id`),
  key choice_question_question_no(`question_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
