package fun.ciallo.blog.core.user.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class UserUpdateDto{
    private Integer id;
    private String nickname;
    private String avatar;
    private String location;
    private String site;
    private String motto;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private MultipartFile avatarFile;
}
