package fun.ciallo.blog.core.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.blog.core.user.dto.UserDto;
import fun.ciallo.blog.core.user.entity.User;

public interface UserService extends IService<User> {
    UserDto getUserDtoById(int id);

    UserDto loadUserDtoById(int id);
}
