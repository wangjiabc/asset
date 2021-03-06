package com.voucher.manage.daoSQL.annotations;

import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    int value() default 255;
    String name() default "";
    Constraints constraints() default @Constraints;
}
