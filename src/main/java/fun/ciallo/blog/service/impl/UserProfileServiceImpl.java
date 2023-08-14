package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.UserProfile;
import fun.ciallo.blog.service.UserProfileService;
import fun.ciallo.blog.mapper.UserProfileMapper;
import org.springframework.stereotype.Service;

/**
* @author Miya
* @description 针对表【user_profile】的数据库操作Service实现
* @createDate 2023-08-11 17:13:30
*/
@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile>
    implements UserProfileService{

}




