package com.engineer.assist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

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

    private String dateType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createdBy;

    @Override
    public String toString() {
        return "ProjectData{" +
            "id=" + id +
            ", source=" + source +
            ", dataName=" + dataName +
            ", dateType=" + dateType +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createdBy=" + createdBy +
        "}";
    }
}
