package code.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Aspect
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 15)
public class Aop2_2 {

    //切入点
    @Pointcut(value = "execution(* code.service.StudentService.annotationFn())")
    public void pointcut() {

    }

    @Before(value = "pointcut()")
    public void before() {
        log.info("注解方式--before执行");
    }

    @After(value = "pointcut()")
    public void after() {
        log.info("注解方式--after执行");
    }
}
