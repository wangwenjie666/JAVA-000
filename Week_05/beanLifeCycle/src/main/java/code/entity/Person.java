package code.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * person
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Slf4j
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String name;
    private String phone;
    private BeanFactory beanFactory;
    private String beanName;

    public Person() {
        log.info("person 构造器调用...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        log.info("注入属性name = {}", name);
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //BeanFactoryAware接口的方法
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("beanFactoryAware调用");
        this.beanFactory = beanFactory;
    }

    //BeanNameAware接口调用
    @Override
    public void setBeanName(String s) {
        log.info("BeanNameAware接口调用");
        this.beanName = s;
    }

    @Override
    public void destroy() throws Exception {
        log.info("DisposableBean调用");
    }

    //InitializingBean调用
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBean调用");
    }

    public void myInit() {
        log.info("自定义init方法");
    }

    public void myDestory() {
        log.info("自定义destory方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
