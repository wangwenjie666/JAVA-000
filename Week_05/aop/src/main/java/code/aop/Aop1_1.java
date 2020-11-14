package code.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;

/**
 * 前置和后置通知
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Slf4j
public class Aop1_1 implements Ordered {
    public void before() {
        log.info("前置通知执行");
    }

    public void after() {
        log.info("后置通知执行");
    }


    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 5;
    }
}
