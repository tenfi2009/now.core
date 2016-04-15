-- ================================================================================================--
-- author	王腾飞
-- create time	2015-04-12
-- description	病例表(t_case)
-- ================================================================================================--
CREATE TABLE t_case(
   id           bigint not null  PRIMARY KEY auto_increment COMMENT 'ID',
   name    		VARCHAR(36) COMMENT '病人姓名',
   sex          int COMMENT '性别',
   age          int COMMENT '年龄',
   mobile_number    VARCHAR(32) COMMENT '手机号',
   fee_sum_amount   decimal(13,2) COMMENT '药物总金额',
   create_time  DATETIME COMMENT '创建时间',
   update_time  DATETIME COMMENT '更新时间',
   creator      VARCHAR(32) COMMENT '创建人',
   update_user  VARCHAR(32) COMMENT '更新人员'
)ENGINE=InnoDB;
-- ================================================================================================--
-- author	王腾飞
-- create time	2015-04-13
-- description	药物表(t_drug)
-- ================================================================================================--
CREATE TABLE t_drug(
   id           bigint not null  PRIMARY KEY auto_increment COMMENT 'ID',
   parent_id   	bigint COMMENT '父ID',
   drugName     VARCHAR(255) COMMENT '药物名称',
   fee_amount   decimal(13,2) COMMENT '药物金额',
   number   	int COMMENT '数量',
   fee_sum_amount   decimal(13,2) COMMENT '药物总金额',
   create_time  DATETIME COMMENT '创建时间',
   update_time  DATETIME COMMENT '更新时间',
   creator      VARCHAR(32) COMMENT '创建人',
   update_user  VARCHAR(32) COMMENT '更新人员'
)ENGINE=InnoDB;