package com.engineer.assist.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engineer.assist.entity.ProjectFileRel;
import com.engineer.assist.mapper.ProjectFileRelMapper;
import com.engineer.assist.service.ProjectFileRelService;
import org.springframework.stereotype.Service;

@Service
public class ProjectFileServiceImpl  extends ServiceImpl<ProjectFileRelMapper, ProjectFileRel> implements ProjectFileRelService {
}
