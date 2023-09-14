package fun.ciallo.blog.core.archive.dto;

import cn.hutool.core.util.StrUtil;
import fun.ciallo.blog.common.Queryable;
import lombok.Data;

@Data
public class ArchiveQueryDto implements Queryable {
    private String title;
    private Integer createUser;
    private Integer category;

    @Override
    public boolean isQuery() {
        return StrUtil.isNotBlank(title) || createUser != null || category != null;
    }
}
