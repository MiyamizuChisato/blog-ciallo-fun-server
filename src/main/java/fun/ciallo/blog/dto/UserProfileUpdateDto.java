package fun.ciallo.blog.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class UserProfileUpdateDto {
    private Integer id;
    private String nickname;
    private String site;
    private String motto;
    private Integer gender;
    private String location;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private MultipartFile avatarFile;
}
