package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.UserIdentity;
import fun.ciallo.blog.service.UserIdentityService;
import fun.ciallo.blog.mapper.UserIdentityMapper;
import org.springframework.stereotype.Service;

/**
* @author Miya
* @description 针对表【user_identity】的数据库操作Service实现
* @createDate 2023-08-11 16:45:13
*/
@Service
public class UserIdentityServiceImpl extends ServiceImpl<UserIdentityMapper, UserIdentity>
    implements UserIdentityService{

}




