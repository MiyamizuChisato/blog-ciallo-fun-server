package fun.ciallo.blog.controller;

import fun.ciallo.blog.common.response.BlogServerException;
import fun.ciallo.blog.common.response.ResultStatus;
import fun.ciallo.blog.dto.UserLoginDto;
import fun.ciallo.blog.dto.UserRegisterDto;
import fun.ciallo.blog.security.BlogUserDetails;
import fun.ciallo.blog.utils.AssertUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/user/auth")
public class UserAuthController {
    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return userRegisterDto.getEmail();
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        AssertUtils.notNull(authentication, new BlogServerException(ResultStatus.USER_AUTH_ERROR));
        BlogUserDetails userDetails = (BlogUserDetails) authentication.getPrincipal();
        return userDetails.getId().toString();
    }
}
