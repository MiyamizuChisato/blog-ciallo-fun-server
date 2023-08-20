package fun.ciallo.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@TableName(value = "user_auth")
@Data
public class UserAuth implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer userProfileId;
    private String email;
    private String password;
}