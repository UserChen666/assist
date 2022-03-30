package com.engineer.assist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.engineer.assist.entity.ProjectInfo;
import com.engineer.assist.entity.UploadRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadRecordMapper extends BaseMapper<UploadRecord> {

}
