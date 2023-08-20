package fun.ciallo.blog.vo;

import fun.ciallo.blog.entity.Archive;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArchiveVo {
    private Integer id;
    private String title;
    private String image;
    private String category;
    private String description;
    private String content;
    private Integer watchCount;
    private Integer likeCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
