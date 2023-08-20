package fun.ciallo.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import fun.ciallo.blog.entity.UserProfile;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.UserIdentityService;
import fun.ciallo.blog.service.UserProfileService;
import fun.ciallo.blog.vo.UserProfileVo;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user/profile")
public class UserProfileController {
    @Resource
    private UserProfileService userProfileService;
    @Resource
    private UserIdentityService userIdentityService;

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
    public void update(@RequestBody UserProfile userProfile) {
        userProfileService.updateById(userProfile);
    }
}
