package fun.ciallo.blog.core.category.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.ciallo.blog.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@TableName
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String remark;
}
