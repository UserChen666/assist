package com.engineer.assist.web;

import com.engineer.assist.entity.*;
import com.engineer.assist.resp.Resp;
import com.engineer.assist.service.IProjectService;
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
    Resp<List<ProjectDTO>> list(@RequestBody ProjectDTO projectDTO) {
        List<ProjectDTO> res = iProjectService.search(projectDTO);
        return Resp.buildSuccess(res);
    }

    @PostMapping("/updateProject")
    Resp<Boolean> updateProject(@RequestBody ProjectInfo project) {
        boolean b = iProjectService.updateById(project);
        return Resp.buildSuccess(b);
    }
    @PostMapping("/changeCategory")
    Resp<Boolean> updateProject(@RequestBody ProjectCategoryRel projectCategoryRel) {
        boolean b = iProjectService.updateCategory(projectCategoryRel);
        return Resp.buildSuccess(b);
    }

    @PostMapping("/updateProjectData")
    @ResponseBody
    Resp<Boolean> updateProject(@RequestBody ProjectData project) {
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
