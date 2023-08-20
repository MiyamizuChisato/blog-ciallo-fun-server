package fun.ciallo.blog.security;

import org.springframework.http.HttpMethod;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Open {
    HttpMethod value();
}
