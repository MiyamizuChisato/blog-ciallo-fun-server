package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.IdentityPermission;
import fun.ciallo.blog.service.IdentityPermissionService;
import fun.ciallo.blog.mapper.IdentityPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Miya
 * @description 针对表【identity_permission】的数据库操作Service实现
 * @createDate 2023-08-11 16:45:13
 */
@Service
public class IdentityPermissionServiceImpl extends ServiceImpl<IdentityPermissionMapper, IdentityPermission>
        implements IdentityPermissionService {

    @Override
    public List<Integer> getPermissionsByIdentities(List<Integer> identities) {
        LambdaQueryWrapper<IdentityPermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(IdentityPermission::getIdentityId, identities);
        List<IdentityPermission> list = this.baseMapper.selectList(lambdaQueryWrapper);
        return list.stream().map(IdentityPermission::getPermissionId).toList();
    }
}




