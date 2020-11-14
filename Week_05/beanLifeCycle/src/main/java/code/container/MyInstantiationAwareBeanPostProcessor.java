package code.container;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * 容器级生命周期方法
 * InstantiationAwareBeanPostProcessor 接口本质是BeanPostProcessor的子接口，一般我们继承Spring为其提供的适配器类InstantiationAwareBeanPostProcessor Adapter来使用它，如下：
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Slf4j
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        log.info("MyInstantiationAwareBeanPostProcessor 构造执行");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        log.info("before 执行");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("after执行");
        return bean ;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        log.info("postProcessPropertyValues执行");
        return pvs;
    }
}
