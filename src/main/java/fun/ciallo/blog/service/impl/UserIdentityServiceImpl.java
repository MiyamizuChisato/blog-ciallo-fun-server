package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.UserIdentity;
import fun.ciallo.blog.service.UserIdentityService;
import fun.ciallo.blog.mapper.UserIdentityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Miya
 * @description 针对表【user_identity】的数据库操作Service实现
 * @createDate 2023-08-11 16:45:13
 */
@Service
public class UserIdentityServiceImpl extends ServiceImpl<UserIdentityMapper, UserIdentity>
        implements UserIdentityService {

    @Override
    public List<Integer> getIdentityIdsByUser(Integer userId) {
        LambdaQueryWrapper<UserIdentity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserIdentity::getUserId, userId);
        List<UserIdentity> list = this.baseMapper.selectList(lambdaQueryWrapper);
        return list.stream().map(UserIdentity::getIdentityId).toList();
    }
}




