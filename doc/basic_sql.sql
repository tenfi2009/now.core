/********************************系统管理模块 开始**************************************************/
-- ================================================================================================--
-- author	阳荣
-- create time	2015-05-12
-- description	组织机构表(t_sys_organization)
-- ================================================================================================--
CREATE TABLE t_sys_organization(
   id           VARCHAR(36)    NOT NULL PRIMARY KEY COMMENT '组织ID',
   parent_id    VARCHAR(36) COMMENT '上级组织ID',
   code         VARCHAR(32) COMMENT '组织编码',
   name         VARCHAR(64) COMMENT '组织名称',
   full_name    VARCHAR(254) COMMENT '组织全称',
   sort_no      INT COMMENT '排序号',
   description  VARCHAR(254) COMMENT '备注',
   degree       INT COMMENT '组织级次',
   is_leaf      BIT COMMENT '是否末级组织',
   level_code   VARCHAR(254) COMMENT '组织级次码',
   status       INT COMMENT '状态',
   create_time  DATETIME COMMENT '创建时间',
   creator      VARCHAR(36) COMMENT '创建人',
   update_time  DATETIME COMMENT '更新时间',
   update_user  VARCHAR(36) COMMENT '更新人员'
)ENGINE=InnoDB;

-- ================================================================================================--
-- author	阳荣
-- create time	2015-05-12
-- description	资源表(t_sys_resource)
-- ================================================================================================--
CREATE TABLE t_sys_resource(
   id           VARCHAR(36)    NOT NULL PRIMARY KEY COMMENT '系统资源ID',
   parent_id    VARCHAR(36) COMMENT '上级资源ID',
   code         VARCHAR(32) COMMENT '资源编码',
   name         VARCHAR(64) COMMENT '资源名称',
   description  VARCHAR(254) COMMENT '描述',
   sort_no      INT COMMENT '排序号',
   degree       INT COMMENT '资源级次',
   full_name    VARCHAR(254) COMMENT '资源全称',
   is_leaf      BIT COMMENT '是否叶子节点',
   level_code   VARCHAR(254) COMMENT '资源层级码',
   icon         VARCHAR(32) COMMENT '图标',
   is_super     BIT COMMENT '是否超级管理人资源',
   permission   VARCHAR(255) COMMENT '权限标识',
   type         INT COMMENT '资源类型',
   uri          VARCHAR(255) COMMENT '资源URI',
   create_time  DATETIME COMMENT '创建时间',
   creator      VARCHAR(36) COMMENT '创建人',
   status       INT COMMENT '状态',
   update_time  DATETIME COMMENT '更新时间',
   update_user  VARCHAR(36) COMMENT '更新用户'
)ENGINE=InnoDB;

-- ================================================================================================--
-- author	阳荣
-- create time	2015-05-12
-- description	角色表(t_sys_role)
-- ================================================================================================--
CREATE TABLE t_sys_role(
   id           VARCHAR(36)    NOT NULL PRIMARY KEY COMMENT '角色ID',
   code         VARCHAR(32) COMMENT '角色编码',
   name         VARCHAR(64) COMMENT '角色名称',
   sort_no      INT COMMENT '排序号',
   description  VARCHAR(254) COMMENT '描述',
   status       INT COMMENT '状态',
   create_time  DATETIME COMMENT '创建时间',
   creator      VARCHAR(36) COMMENT '创建人',
   update_time  DATETIME COMMENT '更新时间',
   update_user  VARCHAR(36) COMMENT '更新人'
)ENGINE=InnoDB;

-- ================================================================================================--
-- author	阳荣
-- create time	2015-05-12
-- description	角色-资源表(t_sys_role_resource)
-- ================================================================================================--
CREATE TABLE t_sys_role_resource(
   id           VARCHAR(36)   NOT NULL PRIMARY KEY COMMENT '主键ID',
   resource_id  VARCHAR(36) COMMENT '资源ID',
   role_id      VARCHAR(36) COMMENT '角色ID'
)ENGINE=InnoDB;

-- ================================================================================================--
-- author	阳荣
-- create time	2015-05-12
-- description	用户表(t_sys_user)
-- ================================================================================================--
CREATE TABLE t_sys_user(
   id           VARCHAR(36)    NOT NULL PRIMARY KEY COMMENT '主键ID',
   code         VARCHAR(32) COMMENT '用户编码',
   name         VARCHAR(64) COMMENT '用户名称',
   account      VARCHAR(32)    NOT NULL COMMENT '登录账号',
   email        VARCHAR(64) COMMENT 'Email',
   is_enable    BIT COMMENT '是否启用',
   mobile       VARCHAR(24) COMMENT '手机号',
   password     VARCHAR(254)   NOT NULL COMMENT '密码',
   phone        VARCHAR(24) COMMENT '电话',
   sort_no      INT COMMENT '排序号',
   description  VARCHAR(254) COMMENT '备注',
   salt         VARCHAR(64)COMMENT '加密串',
   org_id       VARCHAR(36) COMMENT '用户所属组织ID',
   create_time  DATETIME COMMENT '创建日间',
   creator      VARCHAR(36) COMMENT '创建人',
   status       INT COMMENT '状态',
   update_time  DATETIME COMMENT '更新日期',
   update_user  VARCHAR(36) COMMENT '更新人'
)ENGINE=InnoDB;

-- ================================================================================================--
-- author	阳荣
-- create time	2015-05-12
-- description	用户-角色表(t_sys_user_role)
-- ================================================================================================--
CREATE TABLE t_sys_user_role(
   id       VARCHAR(36)   NOT NULL PRIMARY KEY COMMENT '主键ID',
   role_id  VARCHAR(36) COMMENT '角色ID',
   user_id  VARCHAR(36) COMMENT '用户ID'
)ENGINE=InnoDB;

/********************************系统管理模块 结束**************************************************/

/********************************基础数据 开始**************************************************/
-- ================================================================================================--
-- author	
-- create time	
-- description	数据字典(t_bd_dict)
-- ================================================================================================--
create table t_bd_dict
(
   id                   integer not null  PRIMARY KEY auto_increment COMMENT 'ID',
   parent_id            integer COMMENT '上级字典',
   code                 varchar(32) COMMENT '编码',
   name                 varchar(32) COMMENT '名称',
   full_name            varchar(254) COMMENT '全称',
   sort_no              int COMMENT '排序号',
   description          varchar(254) COMMENT '描述',
   status               int COMMENT '状态',
   create_time          DATETIME COMMENT '创建时间',
   creator              varchar(32) COMMENT '创建者',
   update_time          DATETIME COMMENT '最后更新时间',
   update_user          varchar(32) COMMENT '最后更新人员',
   level_code           varchar(254) COMMENT '树的层级编码',
   degree               int COMMENT '树的度',
   is_leaf              boolean COMMENT '是否叶子节点'
)ENGINE=InnoDB;

/********************************基础数据 结束**************************************************/

-- ================================================================================================--
-- author   张芳
-- create time 2016-01-19
-- description 作业表(t_quartz_job)
-- ================================================================================================--
create table t_quartz_job
(
   id                   bigint  NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
   job_group            varchar(32) comment '作业组',
   job_name             varchar(32) comment '作业名称',
   description          varchar(255) comment '作业描述',
   class_name           varchar(255) comment '实现类',
   params               varchar(255) comment '参数'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='作业表';
-- ================================================================================================--
-- author   张芳
-- create time 2016-01-19
-- description 计划设置表(t_quartz_job_scheduler)
-- ================================================================================================--
create table t_quartz_job_scheduler
(
   id                   bigint  NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
   job_id               bigint comment '作业ID',
   scheduler_name       varchar(32) comment '计划名称',
   type                 integer comment '1:一次,2:间隔,3:每天,4:每周,5:每月,6:Cron表达式',
   start_date           datetime comment '开始日期',
   run_time             time comment '运行时间',
   time_interval        integer comment '间隔时间',
   unit                 integer comment '1:秒,2:分,3:小时',
   repeat_count         integer comment '重复次数',
   day                  varchar(64) comment '星期/日',
   cron                 varchar(254) comment '表达式',
   update_time          datetime comment '更新时间',
   update_user          varchar(64) comment '更新人员'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='计划设置表';