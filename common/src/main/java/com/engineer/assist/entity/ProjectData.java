package com.engineer.assist.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.engineer.assist.enumDTO.DataType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@TableName("project_data")
@ApiModel(value = "ProjectData对象", description = "")
@Data
@Entity
public class ProjectData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer projectId;

    private String source;

    private String dataName;

    private DataType dataType;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String createdBy;
    @TableField("pic")
    private String pic;
}
