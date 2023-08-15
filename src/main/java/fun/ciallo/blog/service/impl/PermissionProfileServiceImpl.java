package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.PermissionProfile;
import fun.ciallo.blog.service.IdentityPermissionService;
import fun.ciallo.blog.service.PermissionProfileService;
import fun.ciallo.blog.mapper.PermissionProfileMapper;
import fun.ciallo.blog.service.UserIdentityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miya
 * @description 针对表【permission_profile】的数据库操作Service实现
 * @createDate 2023-08-11 16:45:13
 */
@Service
public class PermissionProfileServiceImpl extends ServiceImpl<PermissionProfileMapper, PermissionProfile>
        implements PermissionProfileService {
    @Resource
    private UserIdentityService userIdentityService;
    @Resource
    private IdentityPermissionService identityPermissionService;

    @Override
    public List<String> listPermissionByUserId(Integer id) {
        List<Integer> identities = userIdentityService.getIdentityIdsByUser(id);
        List<Integer> permissionIds = identityPermissionService.getPermissionsByIdentities(identities);
        List<PermissionProfile> permissionProfiles = this.baseMapper.selectBatchIds(permissionIds);
        return permissionProfiles.stream().map(PermissionProfile::getPermissionName).toList();
    }
}




