package code.annotation;

import java.lang.annotation.*;

/**
 * 从数据源
 *
 * @author wangwenjie
 * @date 2020-12-02
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Slave {
}
