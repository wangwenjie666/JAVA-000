package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(DemoResolver.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    /**
     * 泛型+反射
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T resolve(String serviceClass) {
        try {
            Class<T> classt = (Class<T>) Class.forName(serviceClass);
            return applicationContext.getBean(classt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("类解析异常 = {}", e.getMessage());
            throw new RuntimeException("类解析异常");
        }
    }
}
