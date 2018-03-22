CREATE TABLE `test_info` (
  `test_info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number_of_judge` int NOT NULL DEFAULT 0 COMMENT '判断题数量',
  `value_of_judge` float NOT NULL DEFAULT 0 COMMENT '判断题分值',
  `number_of_select` int NOT NULL DEFAULT 0 COMMENT '选择题数量',
  `value_of_select` float NOT NULL DEFAULT 0 COMMENT '选择题分值',
  
  PRIMARY KEY (`test_info_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
