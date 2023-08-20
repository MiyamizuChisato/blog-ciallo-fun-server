package fun.ciallo.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@TableName(value = "user_profile")
public class UserProfile implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String site;
    private String motto;
    private String avatar;
    private Integer gender;
    private String location;
    private LocalDate birthday;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer isDeleted;
}