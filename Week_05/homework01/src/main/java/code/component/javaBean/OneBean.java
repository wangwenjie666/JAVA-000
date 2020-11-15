package code.component.javaBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * bean
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
@Slf4j
public class OneBean {
    public void beanFn() {
        log.info("Spring bean: OneBean");
    }
}
