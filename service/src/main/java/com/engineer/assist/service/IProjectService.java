package com.engineer.assist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.engineer.assist.entity.*;
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

    boolean deleteById(Integer id);

    List<ProjectDTO> search(ProjectDTO projectDTO);

    boolean updateData(ProjectData project);

    Boolean upload(MultipartFile file,Integer projectId);

    void download(String url);

    List<ProjectFileRel> listFiles(Integer projectId);

    boolean updateCategory(ProjectCategoryRel projectCategoryRel);

    List<UploadRecord> getUploadRecord(Integer projectId);
}
