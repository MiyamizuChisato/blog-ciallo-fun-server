package fun.ciallo.blog.service.impl;

import fun.ciallo.blog.common.response.BlogServerException;
import fun.ciallo.blog.common.response.ResultStatus;
import fun.ciallo.blog.entity.UserAuth;
import fun.ciallo.blog.entity.UserProfile;
import fun.ciallo.blog.security.BlogUserDetails;
import fun.ciallo.blog.service.*;
import fun.ciallo.blog.utils.AssertUtils;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserAuthService userAuthService;
    @Resource
    private UserProfileService userProfileService;
    @Resource
    private PermissionProfileService permissionProfileService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthService.getByEmail(username);
        AssertUtils.notNull(userAuth, new BlogServerException(ResultStatus.USER_AUTH_ERROR));
        UserProfile userProfile = userProfileService.getById(userAuth.getUserProfileId());
        List<String> permissions = permissionProfileService.listPermissionByUserId(userAuth.getUserProfileId());
        BlogUserDetails blogUserDetails = new BlogUserDetails();
        blogUserDetails.setId(userProfile.getId());
        blogUserDetails.setUsername(userAuth.getEmail());
        blogUserDetails.setPassword(userAuth.getPassword());
        blogUserDetails.setPermissions(permissions);
        return blogUserDetails;
    }
}
