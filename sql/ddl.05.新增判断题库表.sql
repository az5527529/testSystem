CREATE TABLE `judge_question` (
  `judge_question_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(256) NOT NULL default '' COMMENT '问题内容',
  `answer` tinyint NOT NULL DEFAULT 0 COMMENT '答案',
  `question_no` varchar(8) NOT NULL DEFAULT '' COMMENT '题目编号',
  
  PRIMARY KEY (`judge_question_id`),
  key choice_question_question_no(`question_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
