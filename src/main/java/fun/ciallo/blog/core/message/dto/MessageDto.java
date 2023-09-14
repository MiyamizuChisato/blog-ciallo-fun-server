package fun.ciallo.blog.core.message.dto;

import fun.ciallo.blog.core.user.dto.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Integer id;
    private String content;
    private LocalDateTime createTime;
    private UserDto replyUser;
    private UserDto createUser;
}
