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

- before和after所在切面的优先级低，则为

```java
[main] INFO code.aop.Aop2_2 - 注解方式--before执行
[main] INFO code.aop.Aop2 - 注解方式--环绕前执行
[main] INFO code.service.StudentService - 执行目标方法 --- student id = 1,name = 张麻子
[main] INFO code.aop.Aop2 - 注解方式--环绕后执行
[main] INFO code.aop.Aop2_2 - 注解方式--after执行
```

- 否则为

```java
[main] INFO code.aop.Aop2 - 注解方式--环绕前执行
[main] INFO code.aop.Aop2_2 - 注解方式--before执行
[main] INFO code.service.StudentService - 执行目标方法 --- student id = 1,name = 张麻子
[main] INFO code.aop.Aop2_2 - 注解方式--after执行
[main] INFO code.aop.Aop2 - 注解方式--环绕后执行
```

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

### SpringBean的装配方式

1. 通过xml方式装配（xml中配置bean id class）

   1. 属性注入（需要提供属性的setter方法）

   ```xml
   //普通属性注入
   <bean id="xmlBean" class="code.component.xml.XmlBean">
           <property name="name" value="zhangsan"/>
           <property name="phone" value="10086"/>
       </bean>
   
   //对象属性注入
   <!--属性注入需要提供属性set方法-->
       <bean id="userService" class="code.component.xml.service.UserService">
           <property name="userDao" ref="userDao"/>
       </bean>
   ```

   2. 构造方法注入（构造方法）

   ```xml
   <!--构造器注入-->
       <bean id="loginService" class="code.component.xml.service.LoginService">
           <constructor-arg ref="userDao"/>
       </bean>
   ```

   3. 工厂方法（实现FactoryBean）

   ```xml
    <!--工厂bean-->
       <bean id="myFactoryBean" class="code.component.xml.factorybean.MyFactoryBean"/>
   ```

   ```java
   public class MyFactoryBean implements FactoryBean<Three> {
       @Override
       public Three getObject() throws Exception {
           return new Three();
       }
   
       @Override
       public Class<?> getObjectType() {
           return Three.class;
       }
   
       @Override
       public boolean isSingleton() {
           return true;
       }
   }
   ```

   4. 自定义工厂（xml配置工厂类和工厂方法）

   ```xml
   <bean id="myFactoryBean2" class="code.component.xml.factorybean.MyFactoryBean2"/>
       <!--指定factory工厂和工厂方法-->
       <bean id="three" factory-bean="myFactoryBean2" factory-method="newInstance"/>
   ```

   ```java
   public class MyFactoryBean2 {
       public Three newInstance() {
           return new Three();
       }
   }
   ```

2. 隐式bean扫描和自动装配（添加@Component等注解表明是组件类型，会被容器创建bean，通过包扫描扫描这些注解所在类的包）

3. javaBean（开启包扫描，使用@Configuration+@Bean的方式装配bean）

## 5.Spring xml配置原理

- spring.handlers文件 ：指定解析xml的handler处理类

```properties
http\://mybeanconfiguration/oneConfiguration=code.handler.OneBeanHandler
```

- xml 节点

```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OneBeanElementTag {
    public static final String ROOT_TAG = "rule";
    public static final String NAME_TAG = "name";
    public static final String PHONE_TAG = "phone";
    public static final String GIRLFRIENDS_TAG = "girlFriends";
}

```

- handler类

```java
public final class OneBeanHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser(OneBeanElementTag.ROOT_TAG, new OneBeanDefinitionParser());
    }
}
```

- 解析类

```java
public final class OneBeanDefinitionParser extends AbstractBeanDefinitionParser {
    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(OneConfiguration.class);
        factory.addConstructorArgValue(element.getAttribute(OneBeanElementTag.PHONE_TAG));
        factory.addConstructorArgValue(element.getAttribute(OneBeanElementTag.NAME_TAG));
        factory.addConstructorArgValue(element.getAttribute(OneBeanElementTag.GIRLFRIENDS_TAG).split(","));
        return factory.getBeanDefinition();
    }
}
```

- 注入bean实体

```java
public class OneConfiguration {
    private final String name;
    private final String phone;
    private final List<String> girlFriends;


    public OneConfiguration(final String name, final String phone, final List<String> girlFriends) {
        this.name = name;
        this.phone = phone;
        this.girlFriends = girlFriends;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getGirlFriends() {
        return girlFriends;
    }

    @Override
    public String toString() {
        return "OneConfiguration{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", girlFriends=" + girlFriends +
                '}';
    }
}
```

- spring.schemas：配置xsd文件路径

```properties
http\://mybeanconfiguration/oneConfiguration/mybean.xsd=META-INF/namespace/mybean.xsd
```

- *.xsd文件：指定xsd文件规范（id节点是必须要传的）

```xml-dtd
<?xml version="1.0" encoding="UTF-8"?>
<xsd:schema xmlns="http://mybeanconfiguration/oneConfiguration"
            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
            targetNamespace="http://mybeanconfiguration/oneConfiguration"
            elementFormDefault="qualified">
    <xsd:import namespace="http://www.springframework.org/schema/beans"
                schemaLocation="http://www.springframework.org/schema/beans/spring-beans.xsd"/>

    <xsd:element name="rule">
        <xsd:complexType>
            <xsd:attribute name="id" type="xsd:string" use="required"/>
            <xsd:attribute name="name" type="xsd:string" use="required"/>
            <xsd:attribute name="phone" type="xsd:string" use="required"/>
            <xsd:attribute name="girlFriends" type="list" use="required"/>
        </xsd:complexType>
    </xsd:element>

    <xsd:simpleType name="list">
        <xsd:list itemType="xsd:string"/>
    </xsd:simpleType>
</xsd:schema>
```

- applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mybean="http://mybeanconfiguration/oneConfiguration"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
                        http://mybeanconfiguration/oneConfiguration
                http://mybeanconfiguration/oneConfiguration/mybean.xsd">

    <!-- mybean.xsd 文件中必须指定id属性，否则会抛出异常 -->
    <mybean:rule id="oneConfiguration" name="张麻子" phone="10086" girlFriends="小张,小李,小王"/>
</beans>
```

- 获取自定义配置类

```java
@Slf4j
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OneConfiguration bean = context.getBean("oneConfiguration", OneConfiguration.class);
        log.info("bean = {}", bean); //bean = OneConfiguration{name='10086', phone='张麻子', girlFriends=[小张, 小李, 小王]}
    }
}

```

- 参考代码：**[自定义xml配置，注入bean到容器中](./homework03)**

## 6.Spring Messing

jms



# Springboot

## 1.简介

简而言之，SpringBoot是搭建Spring环境的一套脚手架工具，关注于自动配置，配置驱动

## 2.核心原理

1. 自动化配置：基于Configuration,Enablexx,Condition
2. spring-boot-starter：脚手架核心，整合了很多三方的组件

## 3.自动配置原理

核心注解 @EnableAutoConfiguration

META-INF\spring.factories 配置文件 包含很多自动配置了 EnableAutoConfiguration = xxx

```java
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
    String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";

    Class<?>[] exclude() default {};

    String[] excludeName() default {};
}
```

AutoConfigurationImportSelector

```java
public String[] selectImports(AnnotationMetadata annotationMetadata) {
        if (!this.isEnabled(annotationMetadata)) {
            return NO_IMPORTS;
        } else {
            //加载配置文件，读取自动配置类
            AutoConfigurationMetadata autoConfigurationMetadata = 
                AutoConfigurationMetadataLoader.loadMetadata(this.beanClassLoader);
```























- 代码：**[nicefish-backend](https://gitee.com/nicefish/nicefish-backend)**
- **[sharding-sphere example]( git clone https://github.com/apache/shardingsphere)**



















