package fun.ciallo.blog.core.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.core.user.dto.UserDto;
import fun.ciallo.blog.core.user.dto.UserQueryDto;
import fun.ciallo.blog.core.user.entity.User;
import fun.ciallo.blog.core.user.mapper.UserMapper;
import fun.ciallo.blog.core.user.service.UserService;
import fun.ciallo.blog.utils.CacheUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public Page<UserDto> loadUserDtoByPage(Page<User> parmaPage) {
        return cacheUtils.pageWithFunction(RedisConstants.ARCHIVE_PAGE, parmaPage, this::getUserDtoByPage, RedisConstants.TTL_LONGER);
    }

    @Override
    public Page<UserDto> getUserDtoByPage(Page<User> parmaPage) {
        parmaPage = this.page(parmaPage);
        return buildUserDtoPage(parmaPage);
    }

    @Override
    public Page<UserDto> queryUserDtoByPage(Page<User> parmaPage, UserQueryDto userQueryDto) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(userQueryDto.getNickname())) {
            lambdaQueryWrapper.like(User::getNickname, userQueryDto.getNickname());
        }
        if (StrUtil.isNotBlank(userQueryDto.getLocation())) {
            lambdaQueryWrapper.like(User::getLocation, userQueryDto.getLocation());
        }
        if (userQueryDto.getGender() != null) {
            lambdaQueryWrapper.eq(User::getGender, userQueryDto.getGender());
        }
        parmaPage = this.page(parmaPage, lambdaQueryWrapper);
        return buildUserDtoPage(parmaPage);
    }

    private Page<UserDto> buildUserDtoPage(Page<User> parmaPage) {
        Page<UserDto> page = new Page<>();
        BeanUtil.copyProperties(parmaPage, page);
        List<UserDto> userDtoList = parmaPage.getRecords().stream().map(user -> BeanUtil.copyProperties(user, UserDto.class)).toList();
        page.setRecords(userDtoList);
        return page;
    }
}
