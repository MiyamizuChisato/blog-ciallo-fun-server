package fun.ciallo.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import fun.ciallo.blog.dto.UserProfileUpdateDto;
import fun.ciallo.blog.entity.UserProfile;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.UserIdentityService;
import fun.ciallo.blog.service.UserProfileService;
import fun.ciallo.blog.vo.UserProfileVo;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

    @Open(HttpMethod.GET)
    @GetMapping("/{id}")
    public UserProfileVo get(@PathVariable int id) {
        UserProfile userProfile = userProfileService.getById(id);
        List<Integer> identityIds = userIdentityService.getIdentityIdsByUser(userProfile.getId());
        UserProfileVo userProfileVo = BeanUtil.copyProperties(userProfile, UserProfileVo.class);
        userProfileVo.setIdentityIds(identityIds);
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
    }
}
