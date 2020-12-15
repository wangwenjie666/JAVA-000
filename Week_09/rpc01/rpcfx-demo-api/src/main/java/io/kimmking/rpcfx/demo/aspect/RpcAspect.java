package io.kimmking.rpcfx.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * rpc aspect.
 *
 * @author Owen
 * @date 2020-12-15
 */
@EnableAspectJAutoProxy
@Component
@Aspect
public class RpcAspect {

    @Pointcut("@annotation(io.kimmking.rpcfx.demo.annontation.Rpc)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object proceed = joinPoint.proceed(args);
    }
}
