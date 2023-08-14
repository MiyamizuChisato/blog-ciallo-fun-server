package fun.ciallo.blog.common.response;

public class BlogServerException extends RuntimeException{
    public BlogServerException(ResultStatus status){
        this.code = status.getCode();
        this.reason = status.getMessage();
    }
    private Integer code;
    private String reason;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
