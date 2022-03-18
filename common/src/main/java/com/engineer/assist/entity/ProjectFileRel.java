package com.engineer.assist.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ProjectFileRel {
    Integer projectId;
    @Id
    Integer id;
    String url;
    String fileName;
}
