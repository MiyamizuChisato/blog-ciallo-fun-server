package fun.ciallo.blog.core.archive.dto;

import lombok.Data;

@Data
public class ArchiveQueryDto {
    private String title;
    private Integer createUser;
    private Integer category;
}
