package fun.ciallo.blog.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NotNull
public class UserRegisterDto {
    @Email
    @NotBlank
    private String email;
    @Length(min = 8, max = 16)
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String code;
    private String site;
    private MultipartFile avatarFile;
    private String avatar;
}
