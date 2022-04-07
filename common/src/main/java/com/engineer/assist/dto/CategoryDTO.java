package com.engineer.assist.dto;

import com.engineer.assist.entity.Category;
import lombok.Data;

import java.util.List;
@Data
public class CategoryDTO {
    Category category;
    List<Integer> projectIds;
}
