package com.suda.tree.controller;

import com.suda.tree.dao.TreePictureRepository;
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
    @DeleteMapping()
    public HttpResult deleteByFileName(@RequestParam("tree_id") String treeId,
                             @RequestParam("file_name") String fileName,
                             @RequestParam("username") String username) throws Exception {
        treePicService.deleteByFileName(treeId, fileName, username);
        return HttpResult.success();
    }

    @ApiOperation("树木图片删除，通过文件URL删除")
    @DeleteMapping()
    public HttpResult deleteByFileURL(@RequestParam("tree_id") String treeId,
                             @RequestParam("file_url") String fileURL,
                             @RequestParam("username") String username) throws Exception {
        treePicService.deleteByFileURL(treeId, fileURL, username);
        return HttpResult.success();
    }
}