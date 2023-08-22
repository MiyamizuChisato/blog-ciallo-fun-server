package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.ciallo.blog.common.response.BlogServerException;
import fun.ciallo.blog.common.response.ResultStatus;
import fun.ciallo.blog.dto.UserLoginDto;
import fun.ciallo.blog.dto.UserRegisterDto;
import fun.ciallo.blog.entity.UserAuth;
import fun.ciallo.blog.entity.UserIdentity;
import fun.ciallo.blog.entity.UserOauth;
import fun.ciallo.blog.entity.UserProfile;
import fun.ciallo.blog.security.BlogUserDetails;
import fun.ciallo.blog.service.*;
import fun.ciallo.blog.utils.AssertUtils;
import fun.ciallo.blog.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class SignServiceImpl implements SignService {
    @Resource
    private UserProfileService userProfileService;
    @Resource
    private UserAuthService userAuthService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserIdentityService userIdentityService;
    @Resource
    private UserOauthService userOauthService;

    @Override
    public String login(UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        AssertUtils.notNull(authentication, new BlogServerException(ResultStatus.USER_AUTH_ERROR));
        BlogUserDetails userDetails = (BlogUserDetails) authentication.getPrincipal();
        return JwtUtils.createToken(userDetails.getId());
    }

    @Override
    @Transactional
    public String register(UserRegisterDto userRegisterDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setNickname(userRegisterDto.getNickname());
        userProfile.setAvatar(userRegisterDto.getAvatar());
        userProfile.setSite(userRegisterDto.getSite());
        userProfileService.save(userProfile);
        UserAuth userAuth = new UserAuth();
        userAuth.setUserProfileId(userProfile.getId());
        userAuth.setEmail(userRegisterDto.getEmail());
        userAuth.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userAuthService.save(userAuth);
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserId(userProfile.getId());
        userIdentityService.save(userIdentity);
        return JwtUtils.createToken(userProfile.getId());
    }

    @Override
    public String register(UserProfile userProfile, UserOauth userOauth) {
        userProfileService.save(userProfile);
        userOauth.setUserProfileId(userProfile.getId());
        userOauthService.save(userOauth);
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserId(userProfile.getId());
        userIdentityService.save(userIdentity);
        return JwtUtils.createToken(userProfile.getId());
    }
}
