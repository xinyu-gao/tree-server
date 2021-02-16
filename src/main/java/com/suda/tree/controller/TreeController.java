package com.suda.tree.controller;

import com.suda.tree.dto.HttpResult;
import com.suda.tree.entity.mysql.TreeInfo;
import com.suda.tree.service.TreeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tree")
public class TreeController {

    @Autowired
    private TreeInfoService treeInfoService;

    @GetMapping("/info")
    public HttpResult getTreeInfo(@RequestParam("tree_id") String treeId){
        return HttpResult.success(treeInfoService.getTreeInfoByTreeId(treeId));
    }



    @PostMapping("/info")
    public HttpResult saveTreeInfo(@RequestBody TreeInfo treeInfo, @RequestParam("username") String username){
        return HttpResult.success(treeInfoService.saveTreeInfo(treeInfo, username));
    }
}
