package com.util;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RBACDao {

    public static void main(String[] args) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************************************分割线************************************************************/
    /* todo Shiro Dao */

    /* 初始化时加载JDBC驱动 */
    public RBACDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/untitled?useSSL=true", "root", "2468");
    }

    public String getPassword(String userName) {
        String sql = "select user_password from shiro_user where user_name = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("user_password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> listRoles(String userName) {
        Set<String> roles = new HashSet<String>();
        String sql = "select r.role_name from shiro_user u "
                + "left join shiro_user_role ur on u.id = ur.user_id "
                + "left join shiro_role r on r.id = ur.role_id "
                + "where u.user_name = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                roles.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public Set<String> listPermissions(String userName) {
        Set<String> permissions = new HashSet<String>();
        String sql = "select p.permission_name from shiro_user u " +
                "left join shiro_user_role ur on u.id = ur.user_id " +
                "left join shiro_role r on r.id = ur.role_id " +
                "left join shiro_role_permission rp on r.id = rp.role_id " +
                "left join shiro_permission p on p.id = rp.permission_id " +
                "where u.user_name =?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                permissions.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissions;
    }

}
