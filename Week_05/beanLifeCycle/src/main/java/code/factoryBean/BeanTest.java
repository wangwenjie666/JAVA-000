package code.factoryBean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-27
 */
public class BeanTest {
    public static void main(String[] args) {
        //通过类路径下的配置文件实例化BeanFactory
        ClassPathResource resource = new ClassPathResource("bean.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(resource);

        OneBean oneBean = beanFactory.getBean("oneBean", OneBean.class);
        System.out.println(oneBean);
    }
}
