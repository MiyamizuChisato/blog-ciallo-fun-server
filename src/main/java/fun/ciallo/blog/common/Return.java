package fun.ciallo.blog.common;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Return {
    boolean value() default false;
}
