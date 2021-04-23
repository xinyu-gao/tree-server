package com.suda.tree.controller;

import com.suda.tree.dao.TreePictureRepository;
import com.suda.tree.dto.param.DeletePicParam;
import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.entity.mysql.TreePicture;
import com.suda.tree.service.TreePicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Api("树木图片管理")
@Slf4j
@RestController
@RequestMapping("/picture/tree")
public class PictureController {

    @Autowired
    private TreePicService treePicService;

    @Autowired
    private TreePictureRepository treePicRepository;

    @ApiOperation("树木图片上传")
    @PostMapping()
    public HttpResult upload(@RequestParam("tree_id") String treeId,
                             @RequestParam("username") String username,
                             @RequestBody MultipartFile file) throws Exception {
        treePicService.upsert(treeId, file, username);
        return HttpResult.success();
    }

    @ApiOperation("树木图片查看")
    @GetMapping()
    public HttpResult picture(@RequestParam("tree_id") String treeId) {
        return HttpResult.success(treePicService.getPicturesById(treeId));
    }

    @ApiOperation("树木图上传测试")
    @PostMapping("/s")
    public HttpResult pictureTest(@RequestBody TreePicture o) {
        return HttpResult.success(treePicRepository.save(o));
    }

    @ApiOperation("树木图片删除,通过图片名删除")
    @DeleteMapping("/name")
    public HttpResult deleteByFileName(@RequestBody DeletePicParam param) throws Exception {
        treePicService.deleteByFileName(param.getTreeId(), param.getFileName(), param.getUsername());
        return HttpResult.success();
    }

    @ApiOperation("树木图片删除，通过文件URL删除")
    @DeleteMapping("/url")
    public HttpResult deleteByFileURL(@RequestBody DeletePicParam param) throws Exception {
        treePicService.deleteByFileURL(param.getTreeId(), param.getFileURL(), param.getUsername());
        return HttpResult.success();
    }
}