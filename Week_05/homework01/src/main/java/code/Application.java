package code;

import code.component.annotation.One;
import code.component.javaBean.BeanConfiguration;
import code.component.javaBean.OneBean;
import code.component.xml.XmlBean;
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

        ApplicationContext annotationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        OneBean oneBean = annotationContext.getBean(OneBean.class);
        oneBean.beanFn();//Spring bean: OneBean
    }
}
