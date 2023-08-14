package fun.ciallo.blog.controller;

import fun.ciallo.blog.vo.UserRegisterVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


@RestController
@RequestMapping("/user/auth")
public class UserAuthController {
    @PostMapping("/register")
    public String register(@Valid @RequestBody UserRegisterVo userRegisterVo){
        return userRegisterVo.getEmail();
    }
}
