# 第八周

## 数据库拆分和分库分表

基于hmily TCC或ShardingSphere的Atomikos XA实现一个简单的分布式 

事务应用demo（二选一）



## hmily tcc分布式事务demo实现

注意点：

- hmily配置文件中的数据库名称必须为hmily

```yml
repository:
  database:
    driverClassName: com.mysql.jdbc.Driver
    url : jdbc:mysql://127.0.0.1:3306/hmily?useUnicode=true&characterEncoding=utf8
    username: root
    password: root
    maxActive: 20
    minIdle: 10
    connectionTimeout: 30000
    idleTimeout: 600000
    maxLifetime: 1800000
```

- 使用了@HmilyTcc注解的方法，必须有父接口，即当前方法必须是一个接口的实现类



主要代码实现：

Order 微服务

```java
@HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void pay(Order order) {

        order.setStatus("begin");
        orderMapper.saveOrder(order);
        //支付
        Account account = new Account();
        account.setUserId("001");
        account.setMoney(10);
        //feign调用account微服务
        accountClient.payAccount(account);

    }

    public void confirm(Order order){
        order.setStatus("submit");
        orderMapper.updateOrder(order);
    }

    public void cancel(Order order){
        order.setStatus("rollback");
        orderMapper.updateOrder(order);
    }
```

Account微服务

```java
@FeignClient("account-server")
public interface AccountClient {

    @PostMapping("/payAccount")
    @Hmily
    String payAccount(@RequestBody Account account);
}
```

```
@HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void payAccount(Account account) {
        System.out.println("开始支付...");
        accountMapper.pay(account);
    }

    public void confirm(Account account){
        System.out.println("支付成功");
    }

    public void cancel(Account account){
        System.out.println("支付失败");
        accountMapper.cancel(account);

    }
```

