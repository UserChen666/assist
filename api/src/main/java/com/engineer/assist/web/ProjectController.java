package com.engineer.assist.web;

import com.engineer.assist.dto.ProjectDTO;
import com.engineer.assist.entity.*;
import com.engineer.assist.exception.ServerException;
import com.engineer.assist.req.ProjectReq;
import com.engineer.assist.resp.Resp;
import com.engineer.assist.result.PageResult;
import com.engineer.assist.result.ProjectResult;
import com.engineer.assist.service.IProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/projectInfo")
@CrossOrigin
public class ProjectController {
    @Autowired
    IProjectService iProjectService;


    @PostMapping("/create")
    @ResponseBody
    Resp<Boolean> create(@RequestBody ProjectDTO projectDTO) {
        boolean b = iProjectService.create(projectDTO).booleanValue();
        return Resp.buildSuccess(b);
    }

    @GetMapping("/get")
    @ResponseBody
    Resp<ProjectDTO> getById(@RequestParam Integer id) {
        ProjectDTO dtoById = iProjectService.getDTOById(id);
        return Resp.buildSuccess(dtoById);
    }

    @GetMapping("/delete")
    @ResponseBody
    Resp<Boolean> deleteById(@RequestParam Integer id) {
        boolean b = iProjectService.deleteById(id);
        return Resp.buildSuccess(b);
    }

    @PostMapping("/list")
    @ResponseBody
    Resp<PageResult<ProjectResult>> list(@RequestBody ProjectReq projectDTO) throws ServerException {
        if(projectDTO.getPageNum() == 0 || projectDTO.getPageNum() == 0) {
            throw new ServerException("page param is zero");
        }
        PageResult<ProjectResult> search = iProjectService.search(projectDTO);
        return Resp.buildSuccess(search);
    }

    @PostMapping("/changeCategory")
    Resp<Boolean> updateProject(@RequestBody ProjectReq projectCategoryRel) {
        boolean b = iProjectService.updateCategory(projectCategoryRel);
        return Resp.buildSuccess(b);
    }

    @PostMapping("/updateProject")
    @ResponseBody
    Resp<Boolean> updateProject(@RequestBody ProjectDTO project) {
        boolean b = iProjectService.updateData(project);
        return Resp.buildSuccess(b);
    }

    @PostMapping("/upload")
    @ResponseBody
    Resp<Boolean> upload(@RequestParam("file") MultipartFile file, Integer projectId) {
        Boolean upload = iProjectService.upload(file, projectId);
        return Resp.buildSuccess(upload);
    }

    @PostMapping("/download")
    @ResponseBody
    Resp<Boolean> download(@RequestParam String url) {
        iProjectService.download(url);
        return Resp.buildSuccess(true);
    }

    @PostMapping("/deleteFile")
    @ResponseBody
    Resp<Boolean> deleteFile(@RequestParam Integer id) {
        iProjectService.deleteFile(id);
        return Resp.buildSuccess(true);
    }
    @PostMapping("/getFileList")
    @ResponseBody
    Resp<List<ProjectFileRel>> getFileList(@RequestParam Integer projectId){
        return Resp.buildSuccess(iProjectService.listFiles(projectId));
    }
    @PostMapping("/getUploadRecord")
    @ResponseBody
    Resp<List<UploadRecord>> getUploadRecord(@RequestParam Integer projectId){
        return Resp.buildSuccess(iProjectService.getUploadRecord(projectId));
    }

}
