package code.autoconfig;

import code.entity.Foo;
import code.properties.FooProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-16
 */
@Configuration
//启用配置
@EnableConfigurationProperties(FooProperties.class)
//类路径包含此类
@ConditionalOnClass(Foo.class)
//配置文件有属性 name和value一样 属性名为 prefix+value/name
//havingValue 期望值 matchIfMissing=true 如果没有配置此属性，默认为匹配成功
@ConditionalOnProperty(prefix = "foo", value = "enable", havingValue = "true", matchIfMissing = true)
public class FooAutoConfiguration {

    @Autowired
    private FooProperties fooProperties;

    @Bean
    @ConditionalOnMissingBean(Foo.class)
    public Foo foo() {
        Foo foo = new Foo();
        foo.setName(fooProperties.getName());
        return foo;
    }
}
