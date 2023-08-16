package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.entity.UserOauth;
import fun.ciallo.blog.service.UserOauthService;
import fun.ciallo.blog.mapper.UserOauthMapper;
import org.springframework.stereotype.Service;

/**
 * @author Miya
 * @description 针对表【user_oauth】的数据库操作Service实现
 * @createDate 2023-08-16 10:04:09
 */
@Service
public class UserOauthServiceImpl extends ServiceImpl<UserOauthMapper, UserOauth>
        implements UserOauthService {

    @Override
    public UserOauth load(UserOauth userOauth) {
        LambdaQueryWrapper<UserOauth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserOauth::getOauthId, userOauth.getOauthId());
        lambdaQueryWrapper.eq(UserOauth::getOauthType, userOauth.getOauthType());
        UserOauth result = this.baseMapper.selectOne(lambdaQueryWrapper);
        return result == null ? userOauth : result;
    }
}




