package fun.ciallo.blog.vo;

import fun.ciallo.blog.entity.UserProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfileVo extends UserProfile {
    private List<Integer> identityIds;
}
