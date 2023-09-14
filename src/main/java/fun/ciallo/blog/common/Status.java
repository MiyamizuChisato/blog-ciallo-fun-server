package fun.ciallo.blog.common;

public enum Status {
    SUCCESS(0, "响应成功"),
    FAILED(-1, "响应失败"),
    NOT_FOUND(-2, "无请求资源"),
    NOT_FOUND_TOKEN(-3, "无请求TOKEN"),
    ILLEGAL(-4, "非法操作"),
    ILLEGAL_TOKEN(-5, "非法TOKEN"),
    ILLEGAL_USER(-6, "非法用户"),
    FORBIDDEN(-7, "用户无权限"),
    USER_AUTH_ERROR(-100, "认证错误"),
    USER_REPEAT(-102, "邮箱已注册"),
    USER_PERMISSION_ERROR(-103, "无权访问"),
    USER_CODE_ERROR(-104, "验证错误"),
    USER_OAUTH_TIMEOUT(-105, "认证超时"),
    CATEGORY_USED(-201, "分类已被使用");

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    }
