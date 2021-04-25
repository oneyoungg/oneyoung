package com.oneyoung.common.message;

import java.lang.annotation.*;

/**
 * 错误Key
 * @author oneyoung
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.FIELD})
public @interface PropertyKey {
    String resourceBundle();
}
