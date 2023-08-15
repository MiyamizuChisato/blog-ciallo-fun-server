package fun.ciallo.blog.controller;

import fun.ciallo.blog.dto.UserLoginDto;
import fun.ciallo.blog.dto.UserRegisterDto;
import fun.ciallo.blog.service.SignService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sign")
public class SignController {
    @Resource
    private SignService signService;

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDto userRegisterDto) {
        return userRegisterDto.getEmail();
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) {
        return signService.login(userLoginDto);
    }
}
