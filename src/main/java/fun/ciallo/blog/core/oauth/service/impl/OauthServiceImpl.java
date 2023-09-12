package fun.ciallo.blog.core.oauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.core.oauth.entity.Oauth;
import fun.ciallo.blog.core.oauth.mapper.OauthMapper;
import fun.ciallo.blog.core.oauth.service.OauthService;
import fun.ciallo.blog.core.user.entity.User;
import fun.ciallo.blog.core.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OauthServiceImpl extends ServiceImpl<OauthMapper, Oauth> implements OauthService {
    @Resource
    private UserService userService;

    @Override
    public void sign(Oauth oauth, User user) {
        userService.save(user);
        oauth.setId(user.getId());
        this.save(oauth);
    }
}
