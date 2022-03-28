package com.engineer.assist.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@TableName("category")
@Table(name = "category")
@ApiModel(value = "Category对象", description = "")
@Data
@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "create_date")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDate;

    @Column(name = "created_by")
    private String createdBy;
    @TableLogic
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "parent_id")
    private Integer parentId;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName=" + categoryName +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", createdBy=" + createdBy +
                ", isActive=" + isActive +
                "}";
    }
}
