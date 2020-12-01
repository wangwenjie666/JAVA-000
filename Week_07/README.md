# 数据库

## 1.批量插入100w数据的效率

- 一条条插入数据，统一提交事务（225s左右）

```java
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
```

- batch执行，统一提交事务（126s）

```java
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
```

## 2.测试查询效率

- 直接分页limit查询（0.74）

```sql
mysql> select * from order1 limit 1000000,2;
+----------+-------------+----------+----------+----------------+-----------+--------------+------------------+----------------------------+--------------+----------------+--------------+
| order_id | phone       | product  | address  | district_price | order_no  | price_amount | shipping_company | shipping_date              | shipping_no  | shipping_price | valid_status |
+----------+-------------+----------+----------+----------------+-----------+--------------+------------------+----------------------------+--------------+----------------+--------------+
|  1000001 | 13111111110 | product0 | address0 |           1.10 | orderNo_0 |         2.20 | company0         | 2020-11-30 00:00:00.000000 | shippingno_0 |           3.30 | 1            |
|  1000002 | 13111111111 | product1 | address1 |           1.10 | orderNo_1 |         2.20 | company1         | 2020-11-30 00:00:00.000000 | shippingno_1 |           3.30 | 1            |
+----------+-------------+----------+----------+----------------+-----------+--------------+------------------+----------------------------+--------------+----------------+--------------+
2 rows in set (0.74 sec)
```

- 找id大于某个值的（0.00）

```sql
mysql> select * from order1 where order_id > 1000000 limit 2;
+----------+-------------+----------+----------+----------------+-----------+--------------+------------------+----------------------------+--------------+----------------+--------------+
| order_id | phone       | product  | address  | district_price | order_no  | price_amount | shipping_company | shipping_date              | shipping_no  | shipping_price | valid_status |
+----------+-------------+----------+----------+----------------+-----------+--------------+------------------+----------------------------+--------------+----------------+--------------+
|  1000001 | 13111111110 | product0 | address0 |           1.10 | orderNo_0 |         2.20 | company0         | 2020-11-30 00:00:00.000000 | shippingno_0 |           3.30 | 1            |
|  1000002 | 13111111111 | product1 | address1 |           1.10 | orderNo_1 |         2.20 | company1         | 2020-11-30 00:00:00.000000 | shippingno_1 |           3.30 | 1            |
+----------+-------------+----------+----------+----------------+-----------+--------------+------------------+----------------------------+--------------+----------------+--------------+
2 rows in set (0.00 sec)
```

## 3.主从复制配置

1. 添加my.ini配置文件（在根目录添加）

   - 配置文件 master

   ```
   [mysqld]
   
   basedir = ./
   datadir = ./data
   port = 3307
   server_id = 1
   
   sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
   log_bin=mysql-bin
   binlog-format=Row
   ```

   - 配置文件slave

   ```
   [mysqld]
   
   basedir = ./
   datadir = ./data
   port = 3308
   server_id = 2
   
   sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
   log_bin=mysql-bin
   binlog-format=Row
   ```

2. 初始化数据库

   ```shell
   PS D:\env\mysql-5.7-master\bin> ./mysqld --initialize-insecure
   ```

3. 启动mysql数据库

   ```
   PS D:\env\mysql-5.7-master\bin> .\mysqld.exe
   ```

4. 连接

   ```
   PS D:\env\mysql-5.7-master\bin> mysql -uroot -P3307
   ```

5. 配置

   - 主节点

   ```
   mysql> create user 'repl'@'%' identified by 'root';
   Query OK, 0 rows affected (0.01 sec)
   
   mysql> grant replication slave on *.* to 'repl'@'%';
   Query OK, 0 rows affected (0.01 sec)
   
   mysql> flush privileges;
   Query OK, 0 rows affected (0.01 sec)
   
   mysql> show master status;
   +------------------+----------+--------------+------------------+-------------------+
   | File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
   +------------------+----------+--------------+------------------+-------------------+
   | mysql-bin.000002 |     1319 |              |                  |                   |
   +------------------+----------+--------------+------------------+-------------------+
   ```

   - 从节点（从新同步要删除从节点的数据库）

   ```
   CHANGE MASTER TO
       MASTER_HOST='localhost',  
       MASTER_PORT = 3307,
       MASTER_USER='repl',      	
       MASTER_PASSWORD='root',   
       MASTER_LOG_FILE='mysql-bin.000002',
       MASTER_LOG_POS=1319;//指向同步的节点
      
   mysql> start slave;
   Query OK, 0 rows affected (0.01 sec)
   ```

## 4.读写分离配置

