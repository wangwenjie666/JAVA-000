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

