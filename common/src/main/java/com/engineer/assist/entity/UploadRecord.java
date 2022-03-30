package com.engineer.assist.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@TableName("upload_record")
@ApiModel(value = "上传记录", description = "")
@Data
@Entity
@Accessors(chain = true)
public class UploadRecord {
    @Id
    private Integer id;

    private String uploader;

    private Integer projectId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
