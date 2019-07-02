/************************************************************分割线************************************************************/
/* todo 数据库结构 */

CREATE TABLE `shiro_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `shiro_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `shiro_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT 0,
  `role_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `shiro_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_status` int(11) NULL DEFAULT 0,
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `permission_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `shiro_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT 0,
  `permission_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

/************************************************************半分割线******************************/
/* shiro_rbac/shiro_web/ssm_cookie_session数据 */

INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (1, 'user1', '123456');
INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (2, 'user2', '123456');
INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (3, 'user3', '123456');

INSERT INTO `shiro_role` VALUES (1, 'admin');
INSERT INTO `shiro_role` VALUES (2, 'user');

INSERT INTO `shiro_user_role`(`id`, `user_id`, `role_id`) VALUES (1, 1, 1); /* user1拥有admin */
INSERT INTO `shiro_user_role`(`id`, `user_id`, `role_id`) VALUES (2, 2, 2); /* user2拥有user */

INSERT INTO `shiro_permission` VALUES (1, 0, '后台首页', '/back/home');
INSERT INTO `shiro_permission` VALUES (2, 0, '查看Model', '/model/list');
INSERT INTO `shiro_permission` VALUES (3, 0, '新增Model', '/model/create');
INSERT INTO `shiro_permission` VALUES (4, 0, '修改Model', '/model/update');
INSERT INTO `shiro_permission` VALUES (5, 0, '删除Model', '/model/delete');

/* admin拥有所有权限 */
INSERT INTO `shiro_role_permission` VALUES (1, 1, 1);
INSERT INTO `shiro_role_permission` VALUES (2, 1, 2);
INSERT INTO `shiro_role_permission` VALUES (3, 1, 3);
INSERT INTO `shiro_role_permission` VALUES (4, 1, 4);
INSERT INTO `shiro_role_permission` VALUES (5, 1, 5);
/* user拥有部分权限 */
INSERT INTO `shiro_role_permission` VALUES (6, 2, 1);

/************************************************************半分割线******************************/
/* shiro_ssm/shiro_springboot/jwt_shiro数据 */

INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (1, 'user1', 'e10adc3949ba59abbe56e057f20f883e'); /* 密码123456 */
INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (2, 'user2', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `shiro_user`(`id`, `user_name`, `user_password`) VALUES (3, 'user3', 'e10adc3949ba59abbe56e057f20f883e');

INSERT INTO `shiro_role` VALUES (1, 'admin');
INSERT INTO `shiro_role` VALUES (2, 'user');

INSERT INTO `shiro_user_role`(`id`, `user_id`, `role_id`) VALUES (1, 1, 1); /* user1拥有admin */
INSERT INTO `shiro_user_role`(`id`, `user_id`, `role_id`) VALUES (2, 2, 2); /* user2拥有user */

INSERT INTO `shiro_permission` VALUES (1, 0, '查看Model', '/model/list');
INSERT INTO `shiro_permission` VALUES (2, 0, '新增Model', '/model/create');
INSERT INTO `shiro_permission` VALUES (3, 0, '修改Model', '/model/update');
INSERT INTO `shiro_permission` VALUES (4, 0, '删除Model', '/model/delete');

/* admin拥有所有权限 */
INSERT INTO `shiro_role_permission` VALUES (1, 1, 1);
INSERT INTO `shiro_role_permission` VALUES (2, 1, 2);
INSERT INTO `shiro_role_permission` VALUES (3, 1, 3);
INSERT INTO `shiro_role_permission` VALUES (4, 1, 4);
/* user拥有部分权限 */
INSERT INTO `shiro_role_permission` VALUES (5, 2, 1);

/************************************************************半分割线******************************/
/* shiro_springboot数据 */

CREATE TABLE `shiro_filter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filter_sort` int(11) NULL DEFAULT 0, /* 值越大，排序越靠后 */
  `filter_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `filter_permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `shiro_filter` VALUES (1, 0, '/', 'anon');
INSERT INTO `shiro_filter` VALUES (2, 0, '/login/**', 'anon');
INSERT INTO `shiro_filter` VALUES (3, 0, '/doLogout', 'logout');
INSERT INTO `shiro_filter` VALUES (4, 1, '/**', 'authc');