package code.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 注解方式配置
 *
 * @author wangwenjie
 * @date 2020-11-14
 */

//声明切面
@Aspect
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class Aop2 {

    //切入点
    @Pointcut(value = "execution(* code.service.StudentService.annotationFn())")
    public void pointcut() {

    }

    @Around(value = "pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("注解方式--环绕前执行");
        joinPoint.proceed();
        log.info("注解方式--环绕后执行");
    }


}
