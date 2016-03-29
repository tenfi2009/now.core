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