package com.util;

import com.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.ArrayList;
import java.util.List;

public class ShiroRBACUtil {

    public static void main(String[] args) {
        try {
            /* 权限 */
            String permitList = "list";
            String permitCreate = "create";
            String permitUpdate = "update";
            String permitDelete = "delete";
            List<String> permissionList = new ArrayList<String>();
            permissionList.add(permitList);
            permissionList.add(permitCreate);
            permissionList.add(permitUpdate);
            permissionList.add(permitDelete);

            /* 角色 */
            String roleAdmin = "admin";
            String roleUser = "user";
            List<String> roleList = new ArrayList<String>();
            roleList.add(roleAdmin);
            roleList.add(roleUser);

            /* 用户 */
            User user1 = new User(1, "user1", "123456"); /* 普通用户 */
            User user2 = new User(2, "user2", "123456"); /* 管理员 */
            User user3 = new User(3, "user3", "123456"); /* 数据库中不存在 */
            List<User> userList = new ArrayList<User>();
            userList.add(user1);
            userList.add(user2);
            userList.add(user3);

            /************************************************************半分割线******************************/

            /* 登录 */
            for (User user : userList) {
                if (login(user))
                    System.out.printf("%s \t登录成功，用的密码是 %s\t %n", user.getName(), user.getPassword());
                else
                    System.out.printf("%s \t登录失败，用的密码是 %s\t %n", user.getName(), user.getPassword());
            }
            System.out.println();

            /* 判断角色 */
            for (User user : userList) {
                for (String role : roleList) {
                    if (login(user)) {
                        if (hasRole(role))
                            System.out.printf("%s\t 拥有角色: %s\t%n", user.getName(), role);
                        else
                            System.out.printf("%s\t 不拥有角色: %s\t%n", user.getName(), role);
                    }
                }
            }
            System.out.println();

            /* 判断权限 */
            for (User user : userList) {
                for (String permit : permissionList) {
                    if (login(user)) {
                        if (isPermitted(permit))
                            System.out.printf("%s\t 拥有权限: %s\t%n", user.getName(), permit);
                        else
                            System.out.printf("%s\t 不拥有权限: %s\t%n", user.getName(), permit);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************************************分割线************************************************************/
    /* todo Shiro RBAC 示例 */

    /*
     * ①SecurityUtils.getSubject()直接获取当前用户，不需要输入参数User
     * ②Subject当前用户
     * */
    private static Subject getSubject() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        /* 全局对象SecurityUtils通过SecurityManager生成Subject实例 */
        Subject subject = SecurityUtils.getSubject();

        return subject;
    }

    private static boolean login(User user) {
        Subject subject = getSubject();

        /* 如果已经登录过了，退出 */
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        /* 封装用户数据 */
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try {
            /* 将用户的数据token，传递到Realm（RBACRealm）中进行对比 */
            subject.login(token);
        } catch (AuthenticationException e) {
            return false;
        }
        return subject.isAuthenticated();
    }

    private static boolean hasRole(String role) {
        Subject subject = getSubject();
        return subject.hasRole(role);
    }

    private static boolean isPermitted(String permit) {
        Subject subject = getSubject();
        try {
            subject.isPermitted(permit);
            return true;
        } catch (IllegalArgumentException e) { /* fixme 没有权限此处会报错 */
            return false;
        }
    }

}
