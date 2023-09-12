package fun.ciallo.blog.core.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.blog.core.oauth.entity.Oauth;
import fun.ciallo.blog.core.user.entity.User;

public interface OauthService extends IService<Oauth> {
    void sign(Oauth oauth, User user);
}
