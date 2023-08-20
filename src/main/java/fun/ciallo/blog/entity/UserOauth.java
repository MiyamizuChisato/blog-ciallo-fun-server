package fun.ciallo.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@TableName(value = "user_oauth")
@Data
public class UserOauth implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer userProfileId;
    private String oauthType;
    private String oauthId;
    private String accessToken;
}