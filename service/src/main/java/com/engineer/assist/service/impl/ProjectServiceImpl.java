package com.engineer.assist.service.impl;

import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engineer.assist.dto.ProjectDTO;
import com.engineer.assist.entity.*;
import com.engineer.assist.enumDTO.DataType;
import com.engineer.assist.enumDTO.ProjectType;
import com.engineer.assist.exception.ServerException;
import com.engineer.assist.mapper.ProjectInfoMapper;
import com.engineer.assist.mapper.ProjectMapper;
import com.engineer.assist.req.ProjectReq;
import com.engineer.assist.result.EnumResult;
import com.engineer.assist.result.PageResult;
import com.engineer.assist.result.PairView;
import com.engineer.assist.result.ProjectResult;
import com.engineer.assist.service.*;
import com.engineer.assist.util.AssistUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private IProjectDataService iProjectDataService;
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    @Autowired
    private IProjectCategoryRelService iProjectCategoryRelService;
    @Autowired
    private ProjectFileRelService projectFileRelService;
    @Autowired
    @Qualifier("ossClient")
    private OSS ossClient;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Autowired
    private IUploadRecordService uploadRecordService;

    @Transactional
    public Boolean create(ProjectDTO projectDTO) {
        projectDTO.getProjectInfo().setIsActive(true);
        boolean save = save(projectDTO.getProject());
        projectDTO.getProjectData().setId(projectDTO.getProject().getId());
        projectDTO.getProjectData().setProjectId(projectDTO.getProject().getId());
        projectDTO.getProjectData().setDataType(DataType.getByName(projectDTO.getProject().getProjectType().getCode()));

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
        ProjectData entity = iProjectDataService.getByProjectId(id);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectInfo(project);
        projectDTO.setProjectData(entity);
        List<ProjectFileRel> projectFileRels = listFiles(id);
        projectDTO.setFiles(projectFileRels);

        List<Integer> categoryIds = iProjectCategoryRelService.getCategoryByProjectIds(id);
        projectDTO.setCategoryIds(categoryIds);

        return projectDTO;

    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) throws ServerException {
        boolean b = removeById(id);
        ProjectData entity = iProjectDataService.getByProjectId(id);
        if(entity == null) {
            throw new ServerException("project data is not exist");
        }
        boolean b1 = iProjectDataService.removeById(entity.getId());
        return b && b1;
    }

    @Override
    public PageResult<ProjectResult> search(ProjectReq projectDTO) {
        PageHelper.startPage(projectDTO.getPageNum(),projectDTO.getPageSize());
        List<ProjectResult> p = listForSearch(projectDTO);

        p.forEach(r -> {
            List<ProjectCategoryRel> rel = iProjectCategoryRelService.selectByProjectId(r.getId());

            if(CollectionUtils.isNotEmpty(rel)) {
                List<Integer> property = AssistUtil.getProperty(rel, ProjectCategoryRel::getCategoryId, Integer.class);

                r.setCategoryIds(property);
            }
        });

        PageInfo pageInfo = new PageInfo(p);

        PageResult<ProjectResult> pageResult = new PageResult();
        pageResult.setData(p);
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setPageNum(projectDTO.getPageNum());
        pageResult.setPageSize(projectDTO.getPageSize());

        return pageResult;
    }

    private List<ProjectResult> listForSearch(ProjectReq projectDTO) {
        return projectInfoMapper.listForSearch(projectDTO);
    }

    @Override
    @Transactional
    public boolean updateData(ProjectDTO project) throws ServerException {

        ProjectInfo byId = getById(project.getProject().getId());
        byId.setProjectName(project.getProject().getProjectName());
        byId.setProjectType(project.getProject().getProjectType());
        byId.setDescripetion(project.getProject().getDescripetion());
        updateById(byId);

        ProjectData byProjectId = iProjectDataService.getByProjectId(project.getProject().getId());

        if(byProjectId == null) {
            throw new ServerException("data was wrong");
        }

        ProjectData projectData = project.getProjectData();

        byProjectId.setDataType(DataType.getByName(project.getProject().getProjectType().getCode()));
        byProjectId.setDataName(projectData.getDataName());
        byProjectId.setSource(projectData.getSource());
        byProjectId.setPic(projectData.getPic());

        iProjectDataService.updateById(byProjectId);

        ProjectReq projectReq = new ProjectReq();
        projectReq.setCategoryIds(project.getCategoryIds());
        projectReq.setId(project.getProject().getId());

        updateCategory(projectReq);

        return Boolean.TRUE;
    }

    @Override
    public void deleteFile(Integer id) {
        projectFileRelService.removeById(id);
    }

    @Override
    public Object getEnum() {
        EnumResult enumResult = new EnumResult();
        List<PairView<Integer, String>> projectType = Arrays.stream(ProjectType.values()).map(e -> {
            return new PairView<Integer, String>(e.getCode(), e.getName());
        }).collect(Collectors.toList());
        enumResult.setProjectTypes(projectType);
        return enumResult;
    }

    @Override
    public String uploadForAll(MultipartFile file) {
        String url = "";
        if (!ossClient.doesBucketExist(bucketName))
            ossClient.createBucket(bucketName);
        try {
            String fineName = AssistUtil.getUuid(true) + "." + FileUtil.getSuffix(file.getOriginalFilename());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fineName, file.getInputStream());
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            return ossClient.generatePresignedUrl(bucketName, fineName, expiration).toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return "";
        }
    }

    @Override
    @Transactional
    public Boolean upload(MultipartFile file, Integer projectId) {
        String url = "";
        if (!ossClient.doesBucketExist(bucketName))
            ossClient.createBucket(bucketName);
        try {
            String fineName = AssistUtil.getUuid(true) + "." + FileUtil.getSuffix(file.getOriginalFilename());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fineName, file.getInputStream());
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            url = ossClient.generatePresignedUrl(bucketName, fineName, expiration).toString();
            ProjectFileRel fileRel = new ProjectFileRel();
            fileRel.setFileName(fineName);
            fileRel.setProjectId(projectId);
            fileRel.setUrl(url);
            projectFileRelService.save(fileRel);

            UploadRecord uploadRecord = new UploadRecord();
            uploadRecord.setProjectId(projectId).setUploader(CurrentUserUtil.getUser().getUserName());
            uploadRecord.setCreateTime(LocalDateTime.now());
            uploadRecordService.save(uploadRecord);
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

    @SneakyThrows
    @Override
    @Transactional
    public boolean updateCategory(ProjectReq projectCategoryRel) {
        if(CollectionUtils.isEmpty(projectCategoryRel.getCategoryIds())) {
            throw new ServerException("param is empty");
        }
        iProjectCategoryRelService.deleteByProjectId(projectCategoryRel.getId());

        projectCategoryRel.getCategoryIds().forEach(c -> {
            ProjectCategoryRel p = new ProjectCategoryRel();

            p.setCategoryId(c);
            p.setProjectId(projectCategoryRel.getId());

            iProjectCategoryRelService.save(p);
        });

        return true;
    }

    @Override
    public List<UploadRecord> getUploadRecord(Integer projectId) {
        List<UploadRecord> list = uploadRecordService.lambdaQuery().eq(UploadRecord::getProjectId, projectId).list();
        return list;
    }
}
