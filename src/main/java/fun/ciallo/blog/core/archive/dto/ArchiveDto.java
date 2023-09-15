package fun.ciallo.blog.core.archive.dto;

import fun.ciallo.blog.core.category.dto.CategoryDto;
import fun.ciallo.blog.core.user.dto.UserArchiveDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArchiveDto {
    private Integer id;
    private String title;
    private String image;
    private String description;
    private String content;
    private Integer watchCount;
    private Integer likeCount;
    private UserArchiveDto createUser;
    private CategoryDto category;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
