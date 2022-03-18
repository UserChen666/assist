package com.engineer.assist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@TableName("category_rel")
@ApiModel(value = "CategoryRel对象", description = "")
@Data
@Entity
public class CategoryRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer categoryId;

    private Integer parentId;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CategoryRel{" +
            "id=" + id +
            ", categoryId=" + categoryId +
            ", parentId=" + parentId +
        "}";
    }
}
