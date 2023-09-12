package fun.ciallo.blog.core.user.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserDto {
    @NotNull
    private Integer id;
    @NotBlank
    private String nickname;
    @NotBlank
    private String avatar;
    private String site;
    private String motto;
    private String gender;
    private Boolean privileges;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
