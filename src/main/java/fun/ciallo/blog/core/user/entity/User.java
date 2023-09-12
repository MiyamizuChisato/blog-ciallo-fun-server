package fun.ciallo.blog.core.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.ciallo.blog.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@TableName
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String avatar;
    private String motto;
    private String gender;
    private LocalDate birthday;
    private String site;
    private Boolean privileges;
}
