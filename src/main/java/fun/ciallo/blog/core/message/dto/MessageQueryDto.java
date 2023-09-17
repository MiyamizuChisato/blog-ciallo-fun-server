package fun.ciallo.blog.core.message.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MessageQueryDto {
    @NotNull
    private Integer parent;
    private String content;
    private Integer createUser;
}
