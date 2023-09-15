package fun.ciallo.blog.core.user.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Integer id;
    private String nickname;
    private String avatar;
    private String location;
    private String site;
    private String motto;
    private Integer gender;
    private Boolean privileges;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private LocalDateTime createTime;
}
