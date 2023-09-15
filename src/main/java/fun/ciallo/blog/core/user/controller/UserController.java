package fun.ciallo.blog.core.user.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ciallo.blog.common.RedisConstants;
import fun.ciallo.blog.common.Status;
import fun.ciallo.blog.common.Token;
import fun.ciallo.blog.common.UserHolder;
import fun.ciallo.blog.core.user.dto.UserDto;
import fun.ciallo.blog.core.user.dto.UserQueryDto;
import fun.ciallo.blog.core.user.dto.UserUpdateDto;
import fun.ciallo.blog.core.user.entity.User;
import fun.ciallo.blog.core.user.service.UserService;
import fun.ciallo.blog.utils.AssertUtils;
import fun.ciallo.blog.utils.CacheUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

@Valid
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private CacheUtils cacheUtils;
    @Resource
    private FileStorageService fileStorageService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id) {
        UserDto userDto = userService.loadUserDtoById(id);
        AssertUtils.notNull(userDto, Status.NOT_FOUND);
        return userDto;
    }

    @Token(admin = true)
    @GetMapping("/page/{current}")
    public Page<UserDto> getUserByPage(@PathVariable long current, @RequestBody UserQueryDto userQueryDto) {
        Page<User> parmaPage = new Page<>(current, 5L);
        Page<UserDto> page;
        if (userQueryDto.isQuery()) {
            page = userService.queryUserDtoByPage(parmaPage, userQueryDto);
        } else {
            page = userService.loadUserDtoByPage(parmaPage);
        }
        AssertUtils.notNull(page, Status.NOT_FOUND);
        return page;
    }

    @Token
    @PutMapping("/update")
    public void update(UserUpdateDto userDto) {
        UserDto holder = UserHolder.get();
        AssertUtils.isEquals(holder.getId(), userDto.getId(), Status.ILLEGAL);
        MultipartFile avatarFile = userDto.getAvatarFile();
        User user = BeanUtil.copyProperties(userDto, User.class);
        if (avatarFile != null) {
            fileStorageService.delete(user.getAvatar());
            FileInfo avatarInfo = fileStorageService.of(avatarFile).setPath("avatar/").setObjectType("image").upload();
            user.setAvatar(avatarInfo.getUrl());
        }
        userService.updateById(user);
        cacheUtils.batchDelete(RedisConstants.USER_PAGE);
        cacheUtils.delete(RedisConstants.USER + user.getId());
    }
}
