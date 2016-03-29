# 初始化管理员
INSERT INTO `t_sys_user` (`id`, `code`, `name`, `account`, `email`, `is_enable`, `mobile`, `password`, `phone`, `sort_no`, `description`, `salt`, `org_id`, `create_time`, `creator`, `status`, `update_time`, `update_user`) VALUES ('4028808144c3bf550144c3cdd2d20000', 'admin', '超级管理员', 'admin', '', '', '', '$shiro1$SHA-256$3$8Gz6VO4eXZjIbxRz1MmGdw==$ROyPE7hQEwdqu8G0KEZOoS19ULMPemlCyphgZgbG/l4=', '', NULL, '', NULL, NULL, '2014-3-15 11:34:09', NULL, 0, NULL, NULL);

INSERT INTO `t_sys_resource` (`id`, `parent_id`, `code`, `name`, `description`, `sort_no`, `degree`, `full_name`, `is_leaf`, `level_code`, `icon`, `is_super`, `permission`, `type`, `uri`, `create_time`, `creator`, `status`, `update_time`, `update_user`) VALUES ('0b6b3f8c-d31e-49a9-a376-f2a237576fbe', 'f39335aa-db36-447c-adc3-8cf1ed22aa56', NULL, '组织管理', '', 1, 1, '系统管理.组织管理', '', '0001-0001', 'menu-icon fa fa-caret-right', '', '', 1, '/sys/org/list', '2015-4-26 16:43:23', '超级管理员', 1, NULL, NULL);
INSERT INTO `t_sys_resource` (`id`, `parent_id`, `code`, `name`, `description`, `sort_no`, `degree`, `full_name`, `is_leaf`, `level_code`, `icon`, `is_super`, `permission`, `type`, `uri`, `create_time`, `creator`, `status`, `update_time`, `update_user`) VALUES ('c686bb9b-924b-472b-b7b0-0e2246a139b9', 'f39335aa-db36-447c-adc3-8cf1ed22aa56', NULL, '用户组管理', '', 5, 1, '系统管理.用户组管理', '', '0001-0005', 'menu-icon fa fa-caret-right', '', '', 1, '/sys/group/list', '2015-7-14 14:57:46', '业务员', 1, '2015-7-14 14:58:05', '业务员');
INSERT INTO `t_sys_resource` (`id`, `parent_id`, `code`, `name`, `description`, `sort_no`, `degree`, `full_name`, `is_leaf`, `level_code`, `icon`, `is_super`, `permission`, `type`, `uri`, `create_time`, `creator`, `status`, `update_time`, `update_user`) VALUES ('d1d5b11b-381d-4608-819d-67e63b5d8c7b', 'f39335aa-db36-447c-adc3-8cf1ed22aa56', NULL, '角色管理', '', 4, 1, '系统管理.角色管理', '', '0001-0004', 'menu-icon fa fa-caret-right', '', '', 1, '/sys/role/list', '2015-4-26 16:46:05', '超级管理员', 1, NULL, NULL);
INSERT INTO `t_sys_resource` (`id`, `parent_id`, `code`, `name`, `description`, `sort_no`, `degree`, `full_name`, `is_leaf`, `level_code`, `icon`, `is_super`, `permission`, `type`, `uri`, `create_time`, `creator`, `status`, `update_time`, `update_user`) VALUES ('ddc990ea-80b8-4eb8-9be2-9f765fd55770', 'f39335aa-db36-447c-adc3-8cf1ed22aa56', NULL, '资源管理', '', 3, 1, '系统管理.资源管理', '', '0001-0003', 'menu-icon fa fa-caret-right', '', '', 1, '/sys/res/list', '2015-4-26 16:45:12', '超级管理员', 1, NULL, NULL);
INSERT INTO `t_sys_resource` (`id`, `parent_id`, `code`, `name`, `description`, `sort_no`, `degree`, `full_name`, `is_leaf`, `level_code`, `icon`, `is_super`, `permission`, `type`, `uri`, `create_time`, `creator`, `status`, `update_time`, `update_user`) VALUES ('f2b41a7e-ea25-4cd5-919b-f391d05b0b2a', 'f39335aa-db36-447c-adc3-8cf1ed22aa56', NULL, '用户管理', '', 2, 1, '系统管理.用户管理', '', '0001-0002', 'menu-icon fa fa-caret-right', '', '', 1, '/sys/user/list', '2015-4-26 16:44:23', '超级管理员', 1, NULL, NULL);

