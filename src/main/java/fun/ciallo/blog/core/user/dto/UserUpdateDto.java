package fun.ciallo.blog.core.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdateDto extends UserDto {
    private MultipartFile avatarFile;
}
