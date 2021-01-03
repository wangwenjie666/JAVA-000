# 学习笔记

## 调试dubbo代码：

1、provider看 Protocol的export
2、consumer看ReferenceConfig
3、provider执行逻辑看Protocol 的handler 



## idea调试问题

1.jdk动态代理在idea进行debug时会调用toString

可以反编译代理类查看toString方法

```java
public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }
```

2.导入maven出现xxx包找不到的情况，删除所有的.iml文件，执行mvn idea:module

## RPC

1.改造自定义 RPC 的程序，提交到 GitHub：

- 尝试将服务端写死查找接口实现类变成泛型和反射；
- 尝试将客户端动态代理改成 AOP，添加异常处理；
- 尝试使用 Netty+HTTP 作为 client 端传输方式。

2.结合 dubbo+hmily，实现一个 TCC 外汇交易处理，代码提交到 GitHub:

- 用户 A 的美元账户和人民币账户都在 A 库，使用 1 美元兑换 7 人民币 ;
- 用户 B 的美元账户和人民币账户都在 B 库，使用 7 人民币兑换 1 美元 ;
- 设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。





作业2：

- 设计两张表，美元账户表，人民币账户表 （分库）
- 资产冻结表
- hmily统一执行tcc

