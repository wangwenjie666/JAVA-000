package code.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * xml方式配置
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Slf4j
public class Aop1 implements Ordered{
    /**
     *
     */
    /*public void before() {
        log.info("前置通知执行");
    }*/

    public void afterReturing() {
        log.info("返回后通知执行");
    }

    public void afterThrowing() {
        log.info("异常通知执行");
    }

    /*public void after() {
        log.info("后置通知执行");
    }*/

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕通知执行 begin");
        joinPoint.proceed();
        log.info("环绕通知执行 end");
    }


    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 10;
    }
}
