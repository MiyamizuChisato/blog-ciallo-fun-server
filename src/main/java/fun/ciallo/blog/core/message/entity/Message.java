package fun.ciallo.blog.core.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import fun.ciallo.blog.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName
@EqualsAndHashCode(callSuper = true)
public class Message extends BaseEntity {
    private Integer id;
    private Integer parent;
    private Integer createUser;
    private Integer replyUser;
    private String content;
}
