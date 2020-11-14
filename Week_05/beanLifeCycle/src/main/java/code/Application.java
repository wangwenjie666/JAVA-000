package code;

import code.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Slf4j
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = context.getBean("person", Person.class);

        log.info("容器获取person = {}", person);

        //关闭容器
        log.info("==>关闭容器...");
        ((ClassPathXmlApplicationContext) context).registerShutdownHook();
        //执行结果
        //[main] INFO code.container.MyBeanPostProcessor - MyBeanPostProcessor构造执行
        //[main] INFO code.container.MyInstantiationAwareBeanPostProcessor - MyInstantiationAwareBeanPostProcessor 构造执行
        //[main] INFO code.container.MyInstantiationAwareBeanPostProcessor - before 执行
        //[main] INFO code.entity.Person - person 构造器调用...
        //[main] INFO code.container.MyInstantiationAwareBeanPostProcessor - postProcessPropertyValues执行
        //[main] INFO code.entity.Person - 注入属性name = 马冬梅
        //[main] INFO code.entity.Person - BeanNameAware接口调用
        //[main] INFO code.entity.Person - beanFactoryAware调用
        //[main] INFO code.container.MyBeanPostProcessor - postProcessBeforeInitialization 修改bean
        //[main] INFO code.entity.Person - InitializingBean调用
        //[main] INFO code.entity.Person - 自定义init方法
        //[main] INFO code.container.MyBeanPostProcessor - postProcessAfterInitialization 修改bean
        //[main] INFO code.container.MyInstantiationAwareBeanPostProcessor - after执行
        //[main] INFO code.Application - 容器获取person = Person{name='马冬梅', phone='110'}
        //[main] INFO code.Application - ==>关闭容器...
        //[Thread-0] INFO code.entity.Person - DisposableBean调用
        //[Thread-0] INFO code.entity.Person - 自定义destory方法

    }
}
