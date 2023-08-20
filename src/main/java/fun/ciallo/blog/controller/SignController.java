package fun.ciallo.blog.controller;

import com.xkcoding.justauth.AuthRequestFactory;
import fun.ciallo.blog.common.response.BlogServerException;
import fun.ciallo.blog.common.response.ResultStatus;
import fun.ciallo.blog.dto.UserLoginDto;
import fun.ciallo.blog.dto.UserRegisterDto;
import fun.ciallo.blog.entity.UserOauth;
import fun.ciallo.blog.entity.UserProfile;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.SignService;
import fun.ciallo.blog.service.UserOauthService;
import fun.ciallo.blog.service.UserProfileService;
import fun.ciallo.blog.utils.AssertUtils;
import fun.ciallo.blog.utils.JwtUtils;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Valid
@RestController
@RequestMapping("/sign")
public class SignController {
    @Resource
    private AuthRequestFactory authRequestFactory;

    @Resource
    private UserOauthService userOauthService;
    @Resource
    private SignService signService;

    @Open(HttpMethod.POST)
    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDto userRegisterDto) {
//        String code = stringRedisTemplate.opsForValue().get("register:" + userRegisterDto.getEmail());
//        AssertUtils.notNull(code, new BlogServerException(ResultStatus.USER_CODE_ERROR));
//        AssertUtils.isEquals(code, userRegisterDto.getCode(), new BlogServerException(ResultStatus.USER_CODE_ERROR));
        return signService.register(userRegisterDto);
    }

    @Open(HttpMethod.POST)
    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) {
        return signService.login(userLoginDto);
    }

    @Open(HttpMethod.POST)
    @PostMapping("/login/{type}")
    public String login(@RequestBody AuthCallback callback, @PathVariable String type) {
        AuthRequest authRequest = authRequestFactory.get(type);
        AuthResponse<AuthUser> authResponse = authRequest.login(callback);
        AuthUser authUser = authResponse.getData();
        AssertUtils.notNull(authUser, new BlogServerException(ResultStatus.USER_OAUTH_TIMEOUT));
        UserOauth userOauth = buildUserOauth(authUser, type);
        userOauth = userOauthService.load(userOauth);
        if (userOauth.getUserProfileId() == null) {
            UserProfile userProfile = buildUserProfile(authUser);
            return signService.register(userProfile, userOauth);
        }
        return JwtUtils.createToken(userOauth.getUserProfileId());
    }

    @Open(HttpMethod.GET)
    @GetMapping("/path/{type}")
    public String getOauthPath(@PathVariable String type) {
        AuthRequest authRequest = authRequestFactory.get(type);
        return authRequest.authorize(AuthStateUtils.createState());
    }

    private UserOauth buildUserOauth(AuthUser authUser, String type) {
        UserOauth userOauth = new UserOauth();
        userOauth.setOauthType(type);
        userOauth.setOauthId(authUser.getUuid());
        userOauth.setAccessToken(authUser.getToken().getAccessToken());
        return userOauth;
    }

    private UserProfile buildUserProfile(AuthUser authUser) {
        UserProfile userProfile = new UserProfile();
        userProfile.setNickname(authUser.getUsername());
        userProfile.setAvatar(authUser.getAvatar());
        return userProfile;
    }
}
