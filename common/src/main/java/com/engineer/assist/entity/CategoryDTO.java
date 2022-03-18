package com.engineer.assist.entity;

import lombok.Data;

import java.util.List;
@Data
public class CategoryDTO {
    Category category;
    List<Integer> projectIds;
}
