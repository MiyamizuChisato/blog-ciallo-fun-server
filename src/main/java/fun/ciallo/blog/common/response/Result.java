package fun.ciallo.blog.common.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public final class Result<T> {
    public Result(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public Result(BlogServerException exception) {
        this.code = exception.getCode();
        this.message = exception.getReason();
    }

    private T data;
    private Integer code;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

}
