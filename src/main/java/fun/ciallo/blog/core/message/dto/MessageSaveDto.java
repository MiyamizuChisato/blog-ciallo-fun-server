package fun.ciallo.blog.core.message.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MessageSaveDto {
    private Integer parent;
    private Integer replyUser;
    @NotBlank
    private String content;
}
