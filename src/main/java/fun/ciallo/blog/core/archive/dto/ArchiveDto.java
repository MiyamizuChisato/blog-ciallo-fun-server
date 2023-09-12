package fun.ciallo.blog.core.archive.dto;

import fun.ciallo.blog.core.category.dto.CategoryDto;
import fun.ciallo.blog.core.user.dto.UserArchiveDto;
import lombok.Data;

@Data
public class ArchiveDto {
    private Integer id;
    private String title;
    private String image;
    private String description;
    private String content;
    private String watchCount;
    private String likeCount;
    private UserArchiveDto createUser;
    private CategoryDto category;
}
