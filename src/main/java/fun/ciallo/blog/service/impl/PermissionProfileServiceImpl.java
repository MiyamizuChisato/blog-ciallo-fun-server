package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.PermissionProfile;
import fun.ciallo.blog.service.PermissionProfileService;
import fun.ciallo.blog.mapper.PermissionProfileMapper;
import org.springframework.stereotype.Service;

/**
* @author Miya
* @description 针对表【permission_profile】的数据库操作Service实现
* @createDate 2023-08-11 16:45:13
*/
@Service
public class PermissionProfileServiceImpl extends ServiceImpl<PermissionProfileMapper, PermissionProfile>
    implements PermissionProfileService{

}




