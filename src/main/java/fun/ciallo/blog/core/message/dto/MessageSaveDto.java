package fun.ciallo.blog.core.message.dto;

import lombok.Data;

@Data
public class MessageSaveDto {
    private Integer parent;
    private Integer replyUser;
    private String content;
}
