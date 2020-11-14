# Spring

## 1.Spring Framework（spring框架）

6大模块：

- Core 
- Testing
- Data access
- Spring mvc/WebFlux
- Integration
- Languages

## 2.Spring Aop

### 1.面向切面编程（字节码增强，中间层代理）

- 有接口实现：默认使用JDK动态代理 $Proxy
- proxyTargetClass = true cglibProxy
- 无接口实现：cglib

### 2.Aop相关概念

- 切面（Aspect）：关注点的模块化（包含了其他概念的这样一个集合），可能会切多个对象，通过xml或者@AspectJ实现
- 连接点（Join Point）：程序执行的某个特定的点，比如方法调用或者异常处理
- 通知（advice）：在切面的某个特定连接点上的操作，包括（around，before，after）等
- 切入点（Pointcut）：匹配切入点的方法被拦截
- 引入（Introduction）：声明额外的方法或者字段，在运行期添加
- 目标对象（target Object）：被一个或者多个切面通知的对象，aop会生成一个该代理对象的子类
- aop代理（aop proxy）：aop框架创建的代理对象，在spring aop中可以是jdkproxy也可以是cglib代理
- 织入（weaving）：创建代理对象的过程

### 3.通知（advice）的类型

- 前置通知（Before Advice）：在连接点执行前执行
- 返回后通知（After returing Advice）：在连接点执行成功返回后执行
- 异常通知（After throwing Advice）：方法抛出异常后
- 后置通知（After Advice）：某个连接点退出，不管是正常还是异常退出
- 环绕通知（Around Advice）：包围一个连接点，可以在执行目标方法前后执行额外操作的通知

### 4.xml方式的aop配置

- xml

```xml
<!--xml 配置 aop begin-->
    <!--1.配置aop对象-->
    <bean id="aop1" class="code.aop.Aop1"></bean>

    <!--2.配置aop操作-->
    <aop:config>
        <!--切入点-->
        <aop:pointcut id="pointcut" expression="execution(* code.service.*.*(..))"/>
        <!--配置切面，增强到方法上-->
        <aop:aspect ref="aop1">
            <aop:before method="before" pointcut-ref="pointcut"/>
            <aop:after-returning method="afterReturing" pointcut-ref="pointcut"/>
            <aop:after-throwing method="afterThrowing" pointcut-ref="pointcut"/>
            <aop:after method="after" pointcut-ref="pointcut"/>
            <aop:around method="around" pointcut-ref="pointcut"/>
        </aop:aspect>
    </aop:config>

    <!--xml 配置 aop end-->
```

- aop代码

```java
@Slf4j
public class Aop1 {
    public void before() {
        log.info("前置通知执行");
    }

    public void afterReturing() {
        log.info("返回后通知执行");
    }

    public void afterThrowing() {
        log.info("异常通知执行");
    }

    public void after() {
        log.info("后置通知执行");
    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕通知执行 begin");
        joinPoint.proceed();
        log.info("环绕通知执行 end");
    }
}
```

注意：如果before和after和环绕通知中都有在目标方法前执行的额外方法，默认顺序是不能够保证的，需要实现Ordered接口来保证或者在xml文件中配置 order

### 5.字节码增强和反射

反射不改变字节码问价，字节码增强会改变，类似于基因操作

cglib字节码增强使用（ASM）

- 代码demo：**[xml及注解方式实现的aop](./aop)**

## 3.IOC控制反转,DI依赖注入

对象装配依赖于接口，在运行时注入对应实现类

## 4.spring bean原理

### Spring Bean的生命周期

bean的加载过程：

1. 实例化
2. 属性赋值
3. 初始化
4. 销毁

BeanFactory到ApplicationContext上下文

- BeanNameAware
- BeanFactoryAware
- ApplicationContextAware

![](./bean0.png)

![](./bean.png)

- Bean加载

```java


org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
    
protected Object doCreateBean(final String beanName, final RootBeanDefinition mbd, final Object[] args)
			throws BeanCreationException {
    
}

protected Object initializeBean(final String beanName, final Object bean, RootBeanDefinition mbd) {
    
}
```

- 代码：**[beanLifeCycle](./beanLifeCycle)**
- 参考资料： **[bean生命周期]( https://www.cnblogs.com/zrtqsk/p/3735273.html )**

## 5.Spring xml配置原理



























