package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.IdentityProfile;
import fun.ciallo.blog.service.IdentityProfileService;
import fun.ciallo.blog.mapper.IdentityProfileMapper;
import org.springframework.stereotype.Service;

/**
* @author Miya
* @description 针对表【identity_profile】的数据库操作Service实现
* @createDate 2023-08-11 16:45:13
*/
@Service
public class IdentityProfileServiceImpl extends ServiceImpl<IdentityProfileMapper, IdentityProfile>
    implements IdentityProfileService{

}




