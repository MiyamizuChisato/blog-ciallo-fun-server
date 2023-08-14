package fun.ciallo.blog.config;

import fun.ciallo.blog.common.response.Result;
import fun.ciallo.blog.common.response.BlogServerException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "fun.ciallo.blog.controller")
public class ExceptionConfig {
    @ExceptionHandler(BlogServerException.class)
    public Result<Object> serverExceptionHandler(BlogServerException exception){
        return new Result<>(exception);
    }
}
