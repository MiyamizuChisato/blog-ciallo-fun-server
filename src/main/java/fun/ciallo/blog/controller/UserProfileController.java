package fun.ciallo.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import fun.ciallo.blog.dto.UserProfileUpdateDto;
import fun.ciallo.blog.entity.UserProfile;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.UserIdentityService;
import fun.ciallo.blog.service.UserProfileService;
import fun.ciallo.blog.vo.UserProfileVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/user/profile")
public class UserProfileController {
    @Resource
    private UserProfileService userProfileService;
    @Resource
    private UserIdentityService userIdentityService;
    @Resource
    private FileStorageService fileStorageService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Open(HttpMethod.GET)
    @GetMapping("/{id}")

    public UserProfileVo get(@PathVariable int id) {
        String userProfileJson = stringRedisTemplate.opsForValue().get("user:profile:" + id);
        if (StringUtils.hasLength(userProfileJson)) {
            return JSONUtil.toBean(userProfileJson, UserProfileVo.class);
        }
        UserProfile userProfile = userProfileService.getById(id);
        List<Integer> identityIds = userIdentityService.getIdentityIdsByUser(userProfile.getId());
        UserProfileVo userProfileVo = BeanUtil.copyProperties(userProfile, UserProfileVo.class);
        userProfileVo.setIdentityIds(identityIds);
        stringRedisTemplate.opsForValue().set(
                "user:profile:" + id,
                JSONUtil.toJsonStr(userProfileVo),
                Duration.ofMinutes(20L)
        );
        return userProfileVo;
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('archive:get')")
    public void update(UserProfileUpdateDto userProfileUpdateDto) {
        MultipartFile avatarFile = userProfileUpdateDto.getAvatarFile();
        UserProfile userProfile = BeanUtil.copyProperties(userProfileUpdateDto, UserProfile.class);
        UserProfile beforeProfile = userProfileService.getById(userProfile.getId());
        if (avatarFile != null) {
            if (!beforeProfile.getAvatar().equals("https://cos.blog.ciallo.fun/blog/avatar/avatar.jpg")) {
                fileStorageService.delete(beforeProfile.getAvatar());
            }
            FileInfo avatarInfo = fileStorageService.of(avatarFile)
                    .setPath("avatar/")
                    .setObjectType("avatar")
                    .upload();
            userProfile.setAvatar(avatarInfo.getUrl());
        }
        userProfileService.updateById(userProfile);
        stringRedisTemplate.delete("user:profile:" + userProfile.getId());
    }
}
