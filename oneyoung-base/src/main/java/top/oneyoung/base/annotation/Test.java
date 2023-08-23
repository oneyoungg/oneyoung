package top.oneyoung.base.annotation;

import java.lang.annotation.*;

/**
 * Top
 *
 * @author oneyoung
 * @since 2023-08-23 22:25
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Test {

    int priority() default 0;

    boolean disabled() default false;
}
