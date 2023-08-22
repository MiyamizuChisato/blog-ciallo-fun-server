package fun.ciallo.blog.service;

import fun.ciallo.blog.dto.UserLoginDto;
import fun.ciallo.blog.entity.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Miya
 * @description 针对表【user_auth】的数据库操作Service
 * @createDate 2023-08-11 16:45:13
 */
public interface UserAuthService extends IService<UserAuth> {
    UserAuth getByEmail(String email);

    boolean existsByEmail(String email);
}
