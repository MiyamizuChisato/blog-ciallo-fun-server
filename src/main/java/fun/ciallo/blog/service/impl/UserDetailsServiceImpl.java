package fun.ciallo.blog.service.impl;

import fun.ciallo.blog.entity.IdentityProfile;
import fun.ciallo.blog.entity.PermissionProfile;
import fun.ciallo.blog.entity.UserAuth;
import fun.ciallo.blog.entity.UserProfile;
import fun.ciallo.blog.security.BlogUserDetails;
import fun.ciallo.blog.service.*;
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
    private UserIdentityService userIdentityService;
    @Resource
    private IdentityPermissionService identityPermissionService;
    @Resource
    private PermissionProfileService PermissionProfileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthService.getByEmail(username);
        if (null == userAuth) {
            throw new UsernameNotFoundException("Email or Password Error");
        }
        UserProfile userProfile = userProfileService.getById(userAuth.getUserProfileId());
        List<Integer> identities = userIdentityService.getIdentityIdsByUser(userProfile.getId());
        List<Integer> permissionIds = identityPermissionService.getPermissionsByIdentities(identities);
        List<PermissionProfile> permissions = PermissionProfileService.listByIds(permissionIds);
        BlogUserDetails blogUserDetails = new BlogUserDetails();
        blogUserDetails.setPermissions(permissions);
        blogUserDetails.setId(userProfile.getId());
        blogUserDetails.setStatus(userProfile.getStatus());
        blogUserDetails.setUsername(userAuth.getEmail());
        return blogUserDetails;
    }
}
