package code;

import java.sql.*;

/**
 * 2.使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
 *
 * @author wangwenjie
 * @date 2020-11-16
 */
public class JdbcWithTxAndPrepareStatement {
    public static void main(String[] args) {
        //1.加载数据库驱动
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            final String url = "jdbc:mysql://localhost:3306/myjdbc";
            final String username = "root";
            final String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            //关闭自动提交
            connection.setAutoCommit(false);

            //3.操作数据库
            //1.insert
            final String insertSql = "insert into t_user(name,age) values (?,?),(?,?)";
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, "tony a");
            statement.setInt(2, 19);
            statement.setString(3, "tony b");
            statement.setInt(4, 14);
            statement.execute();

            //2.update
            final String updateSql = "update t_user set name = ? where age = ?";
            statement = connection.prepareStatement(updateSql);
            statement.setString(1, "tony aa");
            statement.setInt(2, 19);
            statement.execute();

            //3.delete
            final String deleteSql = "delete from t_user where age > ?";
            statement = connection.prepareStatement(deleteSql);
            statement.setInt(1, 15);
            statement.execute();
            //提交事务
            connection.commit();

            //4.select
            final String selectSql = "select * from t_user";
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //事务回滚
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    statement = null;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    connection = null;
                }
            }
        }

    }
}
