package io.github.campanula.utils.proxy.component.assign;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CFieldAssign {

    String[] fieldName();
}
