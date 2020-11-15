package code.component.javaBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * java bean装配
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
@Configuration
@ComponentScan(basePackages = "code.component.javaBean")
public class BeanConfiguration {

    @Bean
    public OneBean oneBean(){
        return new OneBean();
    }
}
