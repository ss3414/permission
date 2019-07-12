/************************************************************分割线************************************************************/
/* todo 数据库结构 */

CREATE TABLE `shiro_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `shiro_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `shiro_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT 0,
  `role_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `shiro_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0, /* 0：一级菜单 */
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `permission_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `permission_perm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `shiro_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT 0,
  `permission_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

/************************************************************半分割线******************************/
/* shiro_rbac/shiro_web/ssm_cookie_session数据 */

INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (1, 'user1', '123456');
INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (2, 'user2', '123456');
INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (3, 'user3', '123456');

INSERT INTO `shiro_role` VALUES (1, 'admin');
INSERT INTO `shiro_role` VALUES (2, 'user');

INSERT INTO `shiro_user_role`(`id`, `user_id`, `role_id`) VALUES (1, 1, 1); /* user1拥有admin */
INSERT INTO `shiro_user_role`(`id`, `user_id`, `role_id`) VALUES (2, 2, 2); /* user2拥有user */

INSERT INTO `shiro_permission` VALUES (1, 0, '后台首页', '/back/home', 'back:home');
INSERT INTO `shiro_permission` VALUES (2, 1, '查看Model', '/model/list', 'model:list');
INSERT INTO `shiro_permission` VALUES (3, 1, '新增Model', '/model/create', 'model:create');
INSERT INTO `shiro_permission` VALUES (4, 1, '修改Model', '/model/update', 'model:update');
INSERT INTO `shiro_permission` VALUES (5, 1, '删除Model', '/model/delete', 'model:delete');

/* admin拥有所有权限 */
INSERT INTO `shiro_role_permission` VALUES (1, 1, 1);
INSERT INTO `shiro_role_permission` VALUES (2, 1, 2);
INSERT INTO `shiro_role_permission` VALUES (3, 1, 3);
INSERT INTO `shiro_role_permission` VALUES (4, 1, 4);
INSERT INTO `shiro_role_permission` VALUES (5, 1, 5);
/* user拥有部分权限 */
INSERT INTO `shiro_role_permission` VALUES (6, 2, 1);

/************************************************************半分割线******************************/
/* shiro_ssm/shiro_springboot/jwt_shiro/jwt_dynamic数据 */

INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (1, 'user1', 'e10adc3949ba59abbe56e057f20f883e'); /* 密码123456 */
INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (2, 'user2', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (3, 'user3', 'e10adc3949ba59abbe56e057f20f883e');

INSERT INTO `shiro_role` VALUES (1, 'admin');
INSERT INTO `shiro_role` VALUES (2, 'user');

INSERT INTO `shiro_user_role`(`id`, `user_id`, `role_id`) VALUES (1, 1, 1); /* user1拥有admin */
INSERT INTO `shiro_user_role`(`id`, `user_id`, `role_id`) VALUES (2, 2, 2); /* user2拥有user */

INSERT INTO `shiro_permission` VALUES (1, 0, '用户列表', '/user/list', 'user:list');
INSERT INTO `shiro_permission` VALUES (2, 1, '新增用户', '/user/create', 'user:create');
INSERT INTO `shiro_permission` VALUES (3, 1, '查看用户', '/user/get', 'user:get');
INSERT INTO `shiro_permission` VALUES (4, 1, '修改用户', '/user/update', 'user:update');
INSERT INTO `shiro_permission` VALUES (5, 1, '删除用户', '/user/delete', 'user:delete');
INSERT INTO `shiro_permission` VALUES (6, 0, '角色列表', '/role/list', 'role:list');
INSERT INTO `shiro_permission` VALUES (7, 6, '新增角色', '/role/create', 'role:create');
INSERT INTO `shiro_permission` VALUES (8, 6, '查看角色', '/role/get', 'role:get');
INSERT INTO `shiro_permission` VALUES (9, 6, '修改角色', '/role/update', 'role:update');
INSERT INTO `shiro_permission` VALUES (10, 6, '删除角色', '/role/delete', 'role:delete');
INSERT INTO `shiro_permission` VALUES (11, 0, '权限列表', '/perm/list', 'permission:list');
INSERT INTO `shiro_permission` VALUES (12, 11, '新增权限', '/perm/create', 'permission:create');
INSERT INTO `shiro_permission` VALUES (13, 11, '查看权限', '/perm/get', 'permission:get');
INSERT INTO `shiro_permission` VALUES (14, 11, '修改权限', '/perm/update', 'permission:update');
INSERT INTO `shiro_permission` VALUES (15, 11, '删除权限', '/perm/delete', 'permission:delete');

/* admin拥有所有权限 */
INSERT INTO `shiro_role_permission` VALUES (1, 1, 1);
INSERT INTO `shiro_role_permission` VALUES (2, 1, 2);
INSERT INTO `shiro_role_permission` VALUES (3, 1, 3);
INSERT INTO `shiro_role_permission` VALUES (4, 1, 4);
INSERT INTO `shiro_role_permission` VALUES (5, 1, 5);
INSERT INTO `shiro_role_permission` VALUES (6, 1, 6);
INSERT INTO `shiro_role_permission` VALUES (7, 1, 7);
INSERT INTO `shiro_role_permission` VALUES (8, 1, 8);
INSERT INTO `shiro_role_permission` VALUES (9, 1, 9);
INSERT INTO `shiro_role_permission` VALUES (10, 1, 10);
INSERT INTO `shiro_role_permission` VALUES (11, 1, 11);
INSERT INTO `shiro_role_permission` VALUES (12, 1, 12);
INSERT INTO `shiro_role_permission` VALUES (13, 1, 13);
INSERT INTO `shiro_role_permission` VALUES (14, 1, 14);
INSERT INTO `shiro_role_permission` VALUES (15, 1, 15);
/* user拥有部分权限 */
INSERT INTO `shiro_role_permission` VALUES (16, 2, 1);

/************************************************************半分割线******************************/
/* shiro_springboot数据 */

CREATE TABLE `shiro_filter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filter_sort` int(11) NULL DEFAULT 0, /* 值越大，排序越靠后 */
  `filter_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `filter_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `shiro_filter` VALUES (1, 0, '/', 'anon');
INSERT INTO `shiro_filter` VALUES (2, 0, '/login/**', 'anon');
INSERT INTO `shiro_filter` VALUES (3, 0, '/doLogout', 'logout');
INSERT INTO `shiro_filter` VALUES (4, 1, '/**', 'authc');
INSERT INTO `shiro_filter` VALUES (5, 2, '/user/list', 'perms[user:list]');
INSERT INTO `shiro_filter` VALUES (6, 2, '/user/create', 'perms[user:create]');
INSERT INTO `shiro_filter` VALUES (7, 2, '/user/get', 'perms[user:get]');
INSERT INTO `shiro_filter` VALUES (8, 2, '/user/update', 'perms[user:update]');
INSERT INTO `shiro_filter` VALUES (9, 2, '/user/delete', 'perms[user:delete]');
INSERT INTO `shiro_filter` VALUES (10, 2, '/role/list', 'perms[role:list]');
INSERT INTO `shiro_filter` VALUES (11, 2, '/role/create', 'perms[role:create]');
INSERT INTO `shiro_filter` VALUES (12, 2, '/role/get', 'perms[role:get]');
INSERT INTO `shiro_filter` VALUES (13, 2, '/role/update', 'perms[role:update]');
INSERT INTO `shiro_filter` VALUES (14, 2, '/role/delete', 'perms[role:delete]');
INSERT INTO `shiro_filter` VALUES (15, 2, '/perm/list', 'perms[permission:list]');
INSERT INTO `shiro_filter` VALUES (16, 2, '/perm/create', 'perms[permission:create]');
INSERT INTO `shiro_filter` VALUES (17, 2, '/perm/get', 'perms[permission:get]');
INSERT INTO `shiro_filter` VALUES (18, 2, '/perm/update', 'perms[permission:update]');
INSERT INTO `shiro_filter` VALUES (19, 2, '/perm/delete', 'perms[permission:delete]');

/************************************************************半分割线******************************/
/* jwt_dynamic数据 */

CREATE TABLE `route_route`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route_sort` int(11) NULL DEFAULT 0, /* 值越大，排序越靠后 */
  `route_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `route_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (0, '/', 'anon');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (0, '/login/doLogin', 'anon');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (0, '/doLogout', 'logout');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (1, '/back/home', 'authc');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/user/list', 'perms[user:list]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/user/create', 'perms[user:create]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/user/get', 'perms[user:get]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/user/update', 'perms[user:update]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/user/delete', 'perms[user:delete]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/role/list', 'perms[role:list]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/role/create', 'perms[role:create]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/role/get', 'perms[role:get]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/role/update', 'perms[role:update]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/role/delete', 'perms[role:delete]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/perm/list', 'perms[permission:list]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/perm/create', 'perms[permission:create]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/perm/get', 'perms[permission:get]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/perm/update', 'perms[permission:update]');
INSERT INTO `shiro_route`(`route_sort`, `route_url`, `route_name`) VALUES (2, '/perm/delete', 'perms[permission:delete]');