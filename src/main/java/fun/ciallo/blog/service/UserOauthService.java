package fun.ciallo.blog.service;

import fun.ciallo.blog.entity.UserOauth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Miya
* @description 针对表【user_oauth】的数据库操作Service
* @createDate 2023-08-16 10:04:09
*/
public interface UserOauthService extends IService<UserOauth> {

    UserOauth load(UserOauth userOauth);
}
