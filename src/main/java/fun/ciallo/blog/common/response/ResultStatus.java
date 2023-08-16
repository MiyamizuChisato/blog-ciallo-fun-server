package fun.ciallo.blog.common.response;

public enum ResultStatus {
    SUCCESS(200, "响应成功"),
    FAILED(-100, "响应失败"),
    USER_AUTH_ERROR(-101, "认证错误"),
    USER_REPEAT(-102, "邮箱已注册"),
    USER_PERMISSION_ERROR(-103, "无权访问"),
    USER_CODE_ERROR(-104, "验证错误"),
    USER_OAUTH_TIMEOUT(-105, "认证超时");

    ResultStatus(Integer code, String message) {
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
