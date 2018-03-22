CREATE TABLE `choice_question` (
  `choice_question_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(256) NOT NULL default '' COMMENT '问题内容',
  `keya` varchar(128) NOT NULL DEFAULT '' COMMENT '选项1',
  `keyb` varchar(128) NOT NULL DEFAULT '' COMMENT '选项2',
  `keyc` varchar(128) NOT NULL DEFAULT '' COMMENT '选项3',
  `keyd` varchar(128) NOT NULL DEFAULT '' COMMENT '选项4',
  `answer` varchar(8) NOT NULL DEFAULT '' COMMENT '答案',
  `question_no` varchar(8) NOT NULL DEFAULT '' COMMENT '题目编号',
  
  PRIMARY KEY (`choice_question_id`),
  key choice_question_question_no(`question_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
