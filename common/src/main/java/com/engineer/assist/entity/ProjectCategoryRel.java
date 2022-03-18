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
@TableName("project_category_rel")
@ApiModel(value = "ProjectCategoryRel对象", description = "")
@Data
@Entity
public class ProjectCategoryRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer projectId;

    private Integer categoryId;

    @Override
    public String toString() {
        return "ProjectCategoryRel{" +
            "id=" + id +
            ", projectId=" + projectId +
            ", categoryId=" + categoryId +
        "}";
    }
}
