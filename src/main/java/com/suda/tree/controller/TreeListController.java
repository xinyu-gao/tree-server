package com.suda.tree.controller;

import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.service.TreeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tree_list")
public class TreeListController {

    @Autowired
    private TreeInfoService treeInfoService;

    @GetMapping("/info")
    public HttpResult getTreeList(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "desc", required = false, defaultValue = "0") int desc) {
        return HttpResult.success(treeInfoService.getTreeList(page, size, desc));
    }

    @GetMapping("/info_sorted")
    public HttpResult getTreeListSorted(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("keys") String[] keys,
            @RequestParam(name = "asc") int asc) {
        return HttpResult.success(treeInfoService.getTreeListSorted(page, size, keys, asc));
    }

    @GetMapping("/info/{city}")
    public HttpResult getTreeListByCity(@PathVariable("city") String city) {
        return HttpResult.success(treeInfoService.getTreeList(city));
    }

    @GetMapping("/info/all")
    public HttpResult getTreeListByCity() {
        return HttpResult.success(treeInfoService.getTreeListAll());
    }
}
