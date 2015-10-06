use db_survey;

SET NAMES utf8;

CREATE TABLE `tb_survey_task` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_no` varchar(30) NOT NULL DEFAULT '' COMMENT '答卷任务编号',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '答卷状态',
  `user_account` varchar(30) NOT NULL DEFAULT '' COMMENT '答卷人员的账号',
  `project_id` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '项目编号',
  `complete_survey` varchar(30) NOT NULL DEFAULT '' COMMENT '已经完成的调查问卷',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_task_no` (`task_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '答卷任务表';

CREATE TABLE `tb_project` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `project_name` varchar(30) NOT NULL DEFAULT '' COMMENT '项目名称',
  `survey_count` tinyint(4) NOT NULL DEFAULT 0 COMMENT '项目包含问卷数量',
  `template_ids` varchar(30) NOT NULL DEFAULT '' COMMENT '项目包含的问卷id',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '项目表';

CREATE TABLE `tb_survey_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '问卷模板ID',
  `template_name` varchar(30) NOT NULL DEFAULT '' COMMENT '模板名称',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '任务更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '问卷模板表';

CREATE TABLE `tb_survey_question` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `question_type` tinyint(4) NOT NULL DEFAULT 0  COMMENT '题目类型',
  `question_title` varchar(100)  NOT NULL DEFAULT ''  COMMENT '题目问题',
  `question_content` varchar(100)  NOT NULL DEFAULT ''  COMMENT '题目内容',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '题目表';

CREATE TABLE `tb_template_question_ref` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `template_id` int(10) unsigned NOT NULL DEFAULT 0  COMMENT '模板id',
  `question_id` int(10) unsigned NOT NULL DEFAULT 0  COMMENT '题目id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_template_question` (`template_id`,`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '试卷和题目管理';