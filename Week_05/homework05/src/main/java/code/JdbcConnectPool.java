package code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * 3.配置 Hikari 连接池，改进上述操作。
 *
 * @author wangwenjie
 * @date 2020-11-16
 */
public class JdbcConnectPool {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/myjdbc";
        final String username = "root";
        final String password = "root";

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource dataSource = new HikariDataSource(config);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
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
