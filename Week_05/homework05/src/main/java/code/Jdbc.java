package code;

import java.sql.*;

/**
 * 1.使用 JDBC 原生接口，实现数据库的增删改查操作。
 *
 * @author wangwenjie
 * @date 2020-11-16
 */
public class Jdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取数据库连接
        final String url = "jdbc:mysql://localhost:3306/myjdbc";
        final String username = "root";
        final String password = "root";
        Connection connection = DriverManager.getConnection(url, username, password);

        //3.操作数据库
        //1.insert
        final String insertSql = "insert into t_user(name,age) values ('mary',18),('lucy',12)";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);

        //2.update
        final String updateSql = "update t_user set name = 'jack' where age = 12";
        statement.executeUpdate(updateSql);

        //3.delete
        final String deleteSql = "delete from t_user where age > 15";
        statement.executeUpdate(deleteSql);

        //4.select
        final String selectSql = "select * from t_user";
        ResultSet resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getInt("age"));
        }
    }
}
