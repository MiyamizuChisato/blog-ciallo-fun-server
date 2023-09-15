package fun.ciallo.blog.core.archive.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.ciallo.blog.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName
@EqualsAndHashCode(callSuper = true)
public class Archive extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String image;
    private Integer category;
    private String description;
    private String content;
    private Integer watchCount;
    private Integer likeCount;
    private Integer createUser;
}
