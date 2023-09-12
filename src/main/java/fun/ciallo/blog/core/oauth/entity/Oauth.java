package fun.ciallo.blog.core.oauth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.ciallo.blog.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName
@EqualsAndHashCode(callSuper = true)
public class Oauth extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String source;
    private String uuid;
    private String accessToken;
    private String refreshToken;
}
