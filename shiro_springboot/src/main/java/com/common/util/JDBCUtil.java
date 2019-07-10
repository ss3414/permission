package com.common.util;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class JDBCUtil {

    private String url;

    private String username;

    private String password;

    private String driver;

    /* 初始化JDBCUtil时，手动获取数据库配置 */
    public JDBCUtil() {
        InputStream inputStream = JDBCUtil.class.getResourceAsStream("/application.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            url = properties.getProperty("spring.datasource.druid.url");
            username = properties.getProperty("spring.datasource.druid.username");
            password = properties.getProperty("spring.datasource.druid.password");
            driver = properties.getProperty("spring.datasource.druid.driver-class-name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> select(String SQL) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                Map<String, Object> map = new HashMap<>();
                /* 下标从1开始 */
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    map.put(metaData.getColumnName(i), resultSet.getObject(metaData.getColumnName(i)));
                }
                resultList.add(map);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
