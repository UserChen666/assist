package com.engineer.assist.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engineer.assist.entity.*;
import com.engineer.assist.mapper.ProjectFileRelMapper;
import com.engineer.assist.mapper.ProjectMapper;
import com.engineer.assist.service.IProjectCategoryRelService;
import com.engineer.assist.service.IProjectDataService;
import com.engineer.assist.service.IProjectService;
import com.engineer.assist.service.ProjectFileRelService;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectInfo> implements IProjectService {

    @Autowired
    IProjectDataService iProjectDataService;
    @Autowired
    IProjectCategoryRelService iProjectCategoryRelService;
    @Autowired
    ProjectFileRelService projectFileRelService;
    @Autowired
    @Qualifier("ossClient")
    private OSS ossClient;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;


    public Boolean create(ProjectDTO projectDTO) {
        boolean save = save(projectDTO.getProject());
        projectDTO.getProjectData().setId(projectDTO.getProject().getId());
        boolean save1 = iProjectDataService.save(projectDTO.getProjectData());
        List<Integer> categoryIds = projectDTO.getCategoryIds();
        if (categoryIds.isEmpty()) return save && save1;
        categoryIds.forEach(i -> {
            ProjectCategoryRel projectCategoryRel = new ProjectCategoryRel();
            projectCategoryRel.setProjectId(projectDTO.getProject().getId());
            projectCategoryRel.setCategoryId(i);
            iProjectCategoryRelService.save(projectCategoryRel);
        });
        return save && save1;

    }

    @Override
    public ProjectDTO getDTOById(Integer id) {
        ProjectInfo project = getById(id);
        ProjectData entity = iProjectDataService.lambdaQuery().eq(ProjectData::getProjectId, id).getEntity();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectInfo(project);
        projectDTO.setProjectData(entity);
        return projectDTO;

    }

    @Override
    public boolean deleteById(Integer id) {
        boolean b = removeById(id);
        ProjectData entity = iProjectDataService.lambdaQuery().eq(ProjectData::getProjectId, id).getEntity();
        boolean b1 = iProjectDataService.removeById(entity.getId());
        return b && b1;
    }

    @Override
    public List<ProjectDTO> search(ProjectDTO projectDTO) {
        List<ProjectDTO> res = new ArrayList<>();
        ProjectInfo project = projectDTO.getProject();
        if (CollectionUtils.isEmpty(projectDTO.getCategoryIds())) {
            List<ProjectInfo> list = lambdaQuery().like(ProjectInfo::getProjectName, project.getProjectName())
                    .eq(ProjectInfo::getProjectType, project.getProjectType())
                    .eq(ProjectInfo::getIsActive, project.getIsActive()).list();
            list.forEach(
                    item -> {
                        ProjectDTO temp = new ProjectDTO();
                        temp.setProjectInfo(project);
                        ProjectData entity = iProjectDataService.lambdaQuery().eq(ProjectData::getProjectId, item.getId()).getEntity();
                        temp.setProjectData(entity);
                        res.add(temp);
                    }
            );
        }
        return res;
    }

    @Override
    public boolean updateData(ProjectData project) {
        boolean b = iProjectDataService.updateById(project);
        return b;
    }

    @Override
    public Boolean upload(MultipartFile file, Integer projectId) {
        String url = "";
        if (!ossClient.doesBucketExist(bucketName))
            ossClient.createBucket(bucketName);
        try {
            String fineName = file.getName();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fineName, file.getInputStream());
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            url = ossClient.generatePresignedUrl(bucketName, fineName, expiration).toString();

            ProjectFileRel fileRel = new ProjectFileRel();
            fileRel.setFileName(fineName);
            fileRel.setProjectId(projectId);
            fileRel.setUrl(url);
            projectFileRelService.save(fileRel);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (StringUtils.isEmpty(url)) return false;
            return true;
        }

    }

    @Override
    public void download(String url) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return;
        }
        HttpServletResponse response = servletRequestAttributes.getResponse();
        int bytesum = 0;
        int byteread = 0;
        URL u = null;
        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            URLConnection conn = u.openConnection();
            InputStream inStream = conn.getInputStream();
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                outputStream.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public List<ProjectFileRel> listFiles(Integer projectId) {
        List<ProjectFileRel> list = projectFileRelService.lambdaQuery().eq(ProjectFileRel::getProjectId, projectId).list();
        return list;
    }

    @Override
    public boolean updateCategory(ProjectCategoryRel projectCategoryRel) {
        ProjectCategoryRel one = iProjectCategoryRelService.lambdaQuery().eq(ProjectCategoryRel::getProjectId, projectCategoryRel.getProjectId()).one();
        iProjectCategoryRelService.removeById(one.getId());
        iProjectCategoryRelService.save(projectCategoryRel);
        return true;
    }
}
