package code;

import code.component.annotation.One;
import code.component.javaBean.BeanConfiguration;
import code.component.javaBean.OneBean;
import code.component.xml.XmlBean;
import code.component.xml.dao.UserDao;
import code.component.xml.factorybean.MyFactoryBean;
import code.component.xml.factorybean.Three;
import code.component.xml.service.LoginService;
import code.component.xml.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * root
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
@Slf4j
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        One one = context.getBean(One.class);
        one.oneFn();//Spring Bean : One  Spring Bean: Two

        XmlBean xmlBean = context.getBean("xmlBean", XmlBean.class);
        xmlBean.xmlBeanFn();//Spring xml Bean : name = zhangsan,phone = 10086

        UserDao userDao = context.getBean("userDao", UserDao.class);
        UserService userService = context.getBean("userService", UserService.class);
        userDao.login();
        userService.login();

        LoginService loginService = context.getBean("loginService", LoginService.class);
        loginService.login();

        //获取工厂类生产的bean
        Three three = context.getBean("myFactoryBean",Three.class);
        //获取工厂bean
        MyFactoryBean factoryBean = context.getBean("&myFactoryBean", MyFactoryBean.class);
        three.threeFn();

        Three threeFromMyFactory = context.getBean("three", Three.class);
        threeFromMyFactory.threeFn();

        ApplicationContext annotationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        OneBean oneBean = annotationContext.getBean(OneBean.class);
        oneBean.beanFn();//Spring bean: OneBean


    }
}
