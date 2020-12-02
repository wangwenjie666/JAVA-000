package code.aspect;

import code.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * aop
 *
 * @author wangwenjie
 * @date 2020-12-02
 */
@EnableAspectJAutoProxy
@Aspect
@Component
public class DataSourceAop {
    @Pointcut("@annotation(code.annotation.Master) ")
    public void writePoint() {

    }

    @Pointcut("@annotation(code.annotation.Slave)")
    public void readPoint() {

    }


    @Before("readPoint()")
    public void beforeRead() {
        DataSourceContextHolder.setSlave();
    }

    @Around("writePoint()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        args[args.length - 1] = DataSourceContextHolder.MASTER;
        System.out.println("修改执行参数：" + Arrays.toString(args));
        DataSourceContextHolder.setMaster();

        joinPoint.proceed(args);

        System.out.println("master 写入成功...");

        args[args.length-1] = DataSourceContextHolder.SLAVE;
        System.out.println("修改执行参数：" + Arrays.toString(args));
        DataSourceContextHolder.setSlave();

        joinPoint.proceed(args);

        System.out.println("slave 写入成功...");
    }


}
