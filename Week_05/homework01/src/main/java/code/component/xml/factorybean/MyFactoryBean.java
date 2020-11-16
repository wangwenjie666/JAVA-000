package code.component.xml.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 工厂方式1
 *
 * @author wangwenjie
 * @date 2020-11-16
 */
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
