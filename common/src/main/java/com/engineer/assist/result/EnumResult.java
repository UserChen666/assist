package com.engineer.assist.result;

import com.engineer.assist.enumDTO.ProjectType;
import javafx.util.Pair;
import lombok.Data;

import java.util.List;

@Data
public class EnumResult{
    private List<Pair<Integer, String>> projectTypes;
}
