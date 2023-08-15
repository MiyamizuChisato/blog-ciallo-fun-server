package fun.ciallo.blog.service;

import fun.ciallo.blog.dto.UserLoginDto;

public interface SignService {
    String login(UserLoginDto userLoginDto);
}
