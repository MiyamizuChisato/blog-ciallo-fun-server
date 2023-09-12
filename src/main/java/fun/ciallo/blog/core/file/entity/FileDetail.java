package fun.ciallo.blog.core.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName
public class FileDetail {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String url;
    private Long size;
    private String filename;
    private String original_filename;
    private String base_path;
    private String path;
    private String ext;
    private String contentType;
    private String platform;
    private String thUrl;
    private String thFilename;
    private Long thSize;
    private String thContentType;
    private String objectId;
    private String objectType;
    private String attr;
    private String fileAcl;
    private String thFileAcl;
    private LocalDateTime createTime;
}
