package io.kimmking.rpcfx.demo.annontation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * rpc.
 *
 * @author Owen
 * @date 2020-12-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Rpc {
    @AliasFor("value")
    String url() default "";

    @AliasFor("url")
    String value() default "";
}
