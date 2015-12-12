CREATE TABLE `tb_action` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '事件ID',
  `action_no` varchar(30) NOT NULL DEFAULT '' COMMENT '事件编号',
  `action_name` varchar(40) NOT NULL DEFAULT '' COMMENT '事件名称',
  `action_desc` varchar(60) NOT NULL DEFAULT '' COMMENT '事件描述',
  `action_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '事件类型',
  `content` varchar(2000) NOT NULL DEFAULT '' COMMENT '事件内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_action_no` (`action_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='事件配置表';

CREATE TABLE `tb_health_project` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `project_no` varchar(40) NOT NULL DEFAULT '' COMMENT '项目名称',
  `project_name` varchar(30) NOT NULL DEFAULT '' COMMENT '项目名称',
  `project_desc` varchar(40) NOT NULL COMMENT '项目名称',
  `schedule` varchar(1000) NOT NULL DEFAULT '' COMMENT '项目包含的问卷id',
  `schedule_time_level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '项目包含问卷数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='项目表';

CREATE TABLE `tb_project_task` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_no` varchar(40) NOT NULL DEFAULT '' COMMENT '任务编号',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '任务状态',
  `user_account` varchar(40) NOT NULL DEFAULT '' COMMENT '答卷人员的账号',
  `project_uniq_no` varchar(40) NOT NULL DEFAULT '0' COMMENT '标识用户当前任务的是哪一个项目',
  `schedule_count` tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前任务是第几次推送',
  `action_no` varchar(40) NOT NULL DEFAULT '' COMMENT '已经完成的调查问卷',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `notify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '计划通知时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_task_no` (`task_no`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='答卷任务表';

CREATE TABLE `tb_survey_paper` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '问卷模板ID',
  `paper_name` varchar(30) NOT NULL DEFAULT '' COMMENT '模板名称',
  `paper_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '模板名称',
  `question_ids` varchar(1000) NOT NULL DEFAULT '' COMMENT '题目ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '任务更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8 COMMENT='问卷模板表';

CREATE TABLE `tb_survey_question` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `question_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '题目类型',
  `question_title` varchar(255) NOT NULL DEFAULT '' COMMENT '题目问题',
  `question_content` varchar(255) NOT NULL DEFAULT '' COMMENT '题目内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8 COMMENT='题目表';

CREATE TABLE `tb_task_survey_result` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_no` varchar(30) NOT NULL DEFAULT '' COMMENT '任务编号',
  `paper_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '问卷id',
  `question_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '问题id',
  `result_option` varchar(30) NOT NULL DEFAULT '' COMMENT '答案选项',
  `result_value` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '答案对应的分值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_task_no_paper_id_question_id` (`task_no`,`paper_id`,`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='调查任务结果表';

CREATE TABLE `tb_user_project_ref` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `project_uniq_no` varchar(40) NOT NULL DEFAULT '' COMMENT '唯一标识用户参加的某一次project',
  `user_account` varchar(40) NOT NULL DEFAULT '' COMMENT '用户账号',
  `project_no` varchar(40) NOT NULL DEFAULT '' COMMENT '项目编号',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '当前参加的想买是否有效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户项目关系表';