package code.service;

import java.sql.*;

/**
 * 订单service
 *
 * @author wangwenjie
 * @date 2020-11-30
 */
public class OrderService {

        public static void main(String[] args) {
//        insertOne();
        batchInsert();
    }

    /**
     * 一条条插入
     */
    public static void insertOne(){
        //1.加载数据库驱动
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接
            final String url = "jdbc:mysql://localhost:3306/db_sql?serverTimezone=UTC";
            final String username = "root";
            final String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            //关闭自动提交
            connection.setAutoCommit(false);

            //3.操作数据库
            final String insertSql = "insert into order1(phone, product, address, district_price, order_no, " +
                    "price_amount, shipping_company, shipping_date, shipping_no, shipping_price, valid_status) values(?,?,?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(insertSql);

            Date date = new Date(System.currentTimeMillis());

            long start = System.currentTimeMillis();

            for (int i = 0; i < 1000000; i++) {
                statement.setString(1, "1311111111" + i);
                statement.setString(2, "product" + i);
                statement.setString(3, "address" + i);
                statement.setDouble(4, 1.1);
                statement.setString(5, "orderNo_" + i);
                statement.setDouble(6, 2.2);
                statement.setString(7, "company" + i);
                statement.setDate(8, date);
                statement.setString(9, "shippingno_" + i);
                statement.setDouble(10, 3.3);
                statement.setString(11, "1");
                statement.execute();
            }

            //提交事务
            connection.commit();

            System.out.println("执行批量插入耗时：" + (System.currentTimeMillis() - start)); //225s 694
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


    /**
     * preparedStatement
     */
    public static void batchInsert() {
        //1.加载数据库驱动
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接
            final String url = "jdbc:mysql://localhost:3306/db_sql?serverTimezone=UTC";
            final String username = "root";
            final String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            //关闭自动提交
            connection.setAutoCommit(false);

            //3.操作数据库
            final String insertSql = "insert into order1(phone, product, address, district_price, order_no, " +
                    "price_amount, shipping_company, shipping_date, shipping_no, shipping_price, valid_status) values(?,?,?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(insertSql);

            Date date = new Date(System.currentTimeMillis());
            for (int i = 0; i < 1000000; i++) {
                statement.setString(1, "1311111111" + i);
                statement.setString(2, "product" + i);
                statement.setString(3, "address" + i);
                statement.setDouble(4, 1.1);
                statement.setString(5, "orderNo_" + i);
                statement.setDouble(6, 2.2);
                statement.setString(7, "company" + i);
                statement.setDate(8, date);
                statement.setString(9, "shippingno_" + i);
                statement.setDouble(10, 3.3);
                statement.setString(11, "1");
                statement.addBatch();
            }

            long start = System.currentTimeMillis();

            statement.executeBatch();

            //提交事务
            connection.commit();

            System.out.println("执行批量插入耗时：" + (System.currentTimeMillis() - start)); //126s 649
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
