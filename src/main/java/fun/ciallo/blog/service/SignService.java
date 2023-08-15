package fun.ciallo.blog.service;

import fun.ciallo.blog.dto.UserLoginDto;
import fun.ciallo.blog.dto.UserRegisterDto;

public interface SignService {
    String login(UserLoginDto userLoginDto);

    String register(UserRegisterDto userRegisterDto);
}
