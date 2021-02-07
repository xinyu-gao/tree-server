package com.suda.tree.controller;

import com.suda.tree.dto.HttpResult;
import com.suda.tree.service.MinioService;
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
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    private TreePicService treePicService;

    @ApiOperation("树木图片上传")
    @PostMapping("/upload")
    public HttpResult upload(@RequestParam("tree_id") String treeId,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("username") String username) throws Exception {
        treePicService.upsert(treeId, file, username);
        return HttpResult.success();
    }

    @ApiOperation("树木图片查看")
    @GetMapping("/pic")
    public HttpResult picture(@RequestParam("tree_id") String treeId) {
        return HttpResult.success(treePicService.getPicturesById(treeId));
    }

    @ApiOperation("树木图片删除")
    @PostMapping("/delete")
    public HttpResult delete(@RequestParam("tree_id") String treeId,
                             @RequestParam("fileNameName") String fileName,
                             @RequestParam("username") String username) throws Exception {
        treePicService.delete(treeId, fileName, username);
        return HttpResult.success();
    }
}