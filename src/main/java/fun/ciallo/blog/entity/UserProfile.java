package fun.ciallo.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user_profile
 */
@TableName(value ="user_profile")
@Data
public class UserProfile implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private String site;

    /**
     * 
     */
    private String motto;

    /**
     * 
     */
    private String avatar;

    /**
     * 
     */
    private Integer gender;

    /**
     * 
     */
    private String location;

    /**
     * 
     */
    private Date birthday;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}