package com.engineer.assist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.engineer.assist.dto.ProjectDTO;
import com.engineer.assist.entity.*;
import com.engineer.assist.exception.ServerException;
import com.engineer.assist.req.ProjectReq;
import com.engineer.assist.result.PageResult;
import com.engineer.assist.result.ProjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
public interface IProjectService extends IService<ProjectInfo> {

    Boolean create(ProjectDTO projectDTO);

    ProjectDTO getDTOById(Integer id);

    boolean deleteById(Integer id) throws ServerException;

    PageResult<ProjectResult> search(ProjectReq projectDTO);

    boolean updateData(ProjectDTO project) throws ServerException;

    Boolean upload(MultipartFile file,Integer projectId);

    void download(String url);

    List<ProjectFileRel> listFiles(Integer projectId);

    boolean updateCategory(ProjectReq projectCategoryRel);

    List<UploadRecord> getUploadRecord(Integer projectId);

    void deleteFile(Integer id);

    Object getEnum();

    String uploadForAll(MultipartFile file);
}
