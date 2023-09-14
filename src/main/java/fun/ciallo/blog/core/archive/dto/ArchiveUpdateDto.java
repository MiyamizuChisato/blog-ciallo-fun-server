package fun.ciallo.blog.core.archive.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ArchiveUpdateDto {
    private Integer id;
    private String title;
    private Integer category;
    private String description;
    private MultipartFile image;
    private MultipartFile content;
}
