package com.suda.tree.controller;

import com.suda.tree.dto.City;
import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.entity.mysql.CityCoordinate;
import com.suda.tree.entity.mysql.TreeInfo;
import com.suda.tree.service.TreeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/tree")
public class TreeController {

    @Autowired
    private TreeInfoService treeInfoService;

    @GetMapping("/info")
    public HttpResult getTreeInfo(@RequestParam("tree_id") String treeId){
        return HttpResult.success(treeInfoService.getTreeInfoByTreeId(treeId));
    }



    @PostMapping("/infos")
    public HttpResult saveTreeInfo(@RequestBody TreeInfo treeInfo, @RequestParam("username") String username){
        return HttpResult.success(treeInfoService.saveTreeInfo(treeInfo, username));
    }


}
