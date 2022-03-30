package com.engineer.assist.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engineer.assist.entity.ProjectFileRel;
import com.engineer.assist.entity.UploadRecord;
import com.engineer.assist.mapper.ProjectFileRelMapper;
import com.engineer.assist.mapper.UploadRecordMapper;
import com.engineer.assist.service.IProjectFileSerivce;
import com.engineer.assist.service.IUploadRecordService;
import org.springframework.stereotype.Service;

@Service
public class UploadRecordServiceImpl extends ServiceImpl<UploadRecordMapper, UploadRecord> implements IUploadRecordService {

}
