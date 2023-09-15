package fun.ciallo.blog.core.oauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xkcoding.justauth.AuthRequestFactory;
import fun.ciallo.blog.core.oauth.entity.Oauth;
import fun.ciallo.blog.core.oauth.service.OauthService;
import fun.ciallo.blog.core.user.entity.User;
import fun.ciallo.blog.utils.TokenUtils;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Valid
@RestController
@RequestMapping("/oauth")
public class OauthController {
    @Resource
    private AuthRequestFactory authRequestFactory;
    @Resource
    private OauthService oauthService;

    @GetMapping("/path/{type}")
    public String getOauthPath(@PathVariable String type) {
        AuthRequest authRequest = authRequestFactory.get(type);
        return authRequest.authorize(AuthStateUtils.createState());
    }

    @PostMapping("/login/{source}")
    public String login(@RequestBody AuthCallback callback, @PathVariable String source) {
        AuthRequest authRequest = authRequestFactory.get(source);
        AuthResponse<AuthUser> authResponse = authRequest.login(callback);
        AuthUser authUser = authResponse.getData();
        LambdaQueryWrapper<Oauth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Oauth::getSource, source);
        lambdaQueryWrapper.eq(Oauth::getUuid, authUser.getUuid());
        Oauth oauth = oauthService.getOne(lambdaQueryWrapper);
        if (oauth == null) {
            User user = this.buildUser(authUser);
            oauth = this.buildOauth(authUser, source);
            oauthService.sign(oauth, user);
        }
        return TokenUtils.createToken(oauth.getId());
    }

    private User buildUser(AuthUser authUser) {
        User user = new User();
        user.setNickname(authUser.getUsername());
        user.setAvatar(authUser.getAvatar());
        return user;
    }

    private Oauth buildOauth(AuthUser authUser, String source) {
        Oauth oauth = new Oauth();
        oauth.setSource(source);
        oauth.setUuid(authUser.getUuid());
        oauth.setAccessToken(authUser.getToken().getAccessToken());
        oauth.setRefreshToken(authUser.getToken().getRefreshToken());
        return oauth;
    }
}
