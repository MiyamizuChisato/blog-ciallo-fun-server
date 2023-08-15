package fun.ciallo.blog.common.response;

public enum ResultStatus {
    SUCCESS(200, "Response Successful!"),
    FAILED(-100, "Response Failed!"),
    USER_AUTH_ERROR(-101, "User Authentication Error!"),
    USER_REPEAT(-102, "User Email Already Exists!"),
    USER_PERMISSION_ERROR(-103, "User Permission Error!");

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
