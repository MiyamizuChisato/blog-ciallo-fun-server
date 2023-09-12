package fun.ciallo.blog.config;

import fun.ciallo.blog.common.Result;
import fun.ciallo.blog.common.ServerException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "fun.ciallo.blog")
public class GlobalExceptionConfig {
    @ExceptionHandler(ServerException.class)
    public Result<Object> serverExceptionHandler(ServerException exception){
        return new Result<>(exception);
    }
}
