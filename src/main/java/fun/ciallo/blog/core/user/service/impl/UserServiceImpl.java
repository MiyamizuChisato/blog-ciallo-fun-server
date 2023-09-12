package fun.ciallo.blog.core.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.core.user.dto.UserDto;
import fun.ciallo.blog.core.user.entity.User;
import fun.ciallo.blog.core.user.mapper.UserMapper;
import fun.ciallo.blog.core.user.service.UserService;
import fun.ciallo.blog.utils.CacheUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private CacheUtils cacheUtils;

    @Override
    public UserDto getUserDtoById(int id) {
        User user = this.getById(id);
        return BeanUtil.copyProperties(user, UserDto.class);
    }

    @Override
    public UserDto loadUserDtoById(int id) {
        return cacheUtils.getWithFunction(RedisConstants.USER, id, UserDto.class, this::getUserDtoById, RedisConstants.TTL_LONG);
    }
}
