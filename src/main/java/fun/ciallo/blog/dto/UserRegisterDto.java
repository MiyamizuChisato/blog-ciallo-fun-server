package fun.ciallo.blog.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    private String site;
    private String avatar;
}
