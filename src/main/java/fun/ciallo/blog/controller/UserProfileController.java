package fun.ciallo.blog.controller;

import fun.ciallo.blog.entity.UserProfile;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.UserProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserProfileController {
    @Resource
    private UserProfileService userProfileService;

    @Open
    @GetMapping("/{id}")
    public UserProfile get(@PathVariable int id) {
        return userProfileService.getById(id);
    }
}
