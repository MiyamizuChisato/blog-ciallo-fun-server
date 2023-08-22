package fun.ciallo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.common.response.BlogServerException;
import fun.ciallo.blog.common.response.ResultStatus;
import fun.ciallo.blog.dto.UserLoginDto;
import fun.ciallo.blog.entity.UserAuth;
import fun.ciallo.blog.security.BlogUserDetails;
import fun.ciallo.blog.service.UserAuthService;
import fun.ciallo.blog.mapper.UserAuthMapper;
import fun.ciallo.blog.utils.AssertUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Miya
 * @description 针对表【user_auth】的数据库操作Service实现
 * @createDate 2023-08-11 16:45:13
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth>
        implements UserAuthService {

    @Override
    public UserAuth getByEmail(String email) {
        LambdaQueryWrapper<UserAuth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAuth::getEmail, email);
        return this.baseMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public boolean existsByEmail(String email) {
        LambdaQueryWrapper<UserAuth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAuth::getEmail, email);
        return this.baseMapper.exists(lambdaQueryWrapper);
    }
}




