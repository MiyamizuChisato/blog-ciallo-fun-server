package fun.ciallo.blog.service;

import fun.ciallo.blog.entity.PermissionProfile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Miya
 * @description 针对表【permission_profile】的数据库操作Service
 * @createDate 2023-08-11 16:45:13
 */
public interface PermissionProfileService extends IService<PermissionProfile> {

    List<String> listPermissionByUserId(Integer id);
}
