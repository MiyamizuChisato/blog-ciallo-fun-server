package fun.ciallo.blog.core.archive.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ArchiveSaveDto {
    private String title;
    private String description;
    private Integer category;
    private Integer createUser;
    private MultipartFile image;
    private MultipartFile content;
}
