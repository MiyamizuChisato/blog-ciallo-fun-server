package fun.ciallo.blog.core.message.dto;

import cn.hutool.core.util.StrUtil;
import fun.ciallo.blog.common.Queryable;
import lombok.Data;

@Data
public class MessageQueryDto implements Queryable {
    private String content;
    private Integer createUser;

    @Override
    public boolean isQuery() {
        return StrUtil.isNotBlank(content) || createUser != null;
    }
}
