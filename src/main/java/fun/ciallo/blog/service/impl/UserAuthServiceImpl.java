package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.UserAuth;
import fun.ciallo.blog.service.UserAuthService;
import fun.ciallo.blog.mapper.UserAuthMapper;
import org.springframework.stereotype.Service;

/**
* @author Miya
* @description 针对表【user_auth】的数据库操作Service实现
* @createDate 2023-08-11 16:45:13
*/
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth>
    implements UserAuthService{

}




