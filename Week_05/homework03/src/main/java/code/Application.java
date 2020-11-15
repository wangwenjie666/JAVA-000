package code;

import code.entity.OneConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
@Slf4j
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OneConfiguration bean = context.getBean("oneConfiguration", OneConfiguration.class);
        log.info("bean = {}", bean); //bean = OneConfiguration{name='10086', phone='张麻子', girlFriends=[小张, 小李, 小王]}
    }
}
