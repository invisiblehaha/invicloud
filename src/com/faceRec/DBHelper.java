package com.faceRec;
import java.sql.*;
public class DBHelper {

    public void test(User user) throws Exception {
        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://localhost:3306/user?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user" + "?serverTimezone=GMT%2B8", "root", "123456");
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
         //   System.out.println("123123");
        PreparedStatement statement = conn.prepareStatement("insert into registeruser values(?,?,?,?)");
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getPhone());
        statement.setString(4, user.getId());
        statement.execute();
    } catch (SQLException e) {
            System.out.println("MySQL操作错误");
        e.printStackTrace();
    }
}
}

