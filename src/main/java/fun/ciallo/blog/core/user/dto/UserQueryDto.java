package fun.ciallo.blog.core.user.dto;

import cn.hutool.core.util.StrUtil;
import fun.ciallo.blog.common.Queryable;
import lombok.Data;

@Data
public class UserQueryDto implements Queryable {
    private String nickname;
    private Integer gender;
    private String location;

    @Override
    public boolean isQuery() {
        return StrUtil.isNotBlank(nickname) || StrUtil.isBlankIfStr(location) || gender != null;
    }
}
