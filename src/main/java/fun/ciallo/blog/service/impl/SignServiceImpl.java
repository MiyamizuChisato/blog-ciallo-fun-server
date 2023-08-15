package fun.ciallo.blog.service.impl;

import fun.ciallo.blog.common.response.BlogServerException;
import fun.ciallo.blog.common.response.ResultStatus;
import fun.ciallo.blog.dto.UserLoginDto;
import fun.ciallo.blog.security.BlogUserDetails;
import fun.ciallo.blog.service.SignService;
import fun.ciallo.blog.utils.AssertUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SignServiceImpl implements SignService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        AssertUtils.notNull(authentication, new BlogServerException(ResultStatus.USER_AUTH_ERROR));
        BlogUserDetails userDetails = (BlogUserDetails) authentication.getPrincipal();
        return userDetails.getId().toString();
    }
}
