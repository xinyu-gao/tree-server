package com.suda.tree.controller;

import com.suda.tree.dto.City;
import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.dto.result.PageResult;
import com.suda.tree.entity.mysql.CityCoordinate;
import com.suda.tree.entity.mysql.TreeInfo;
import com.suda.tree.entity.mysql.User;
import com.suda.tree.service.TreeInfoService;
import com.suda.tree.util.PageUtil;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("根据树木编号获取该树木信息")
    @GetMapping("/info")
    public HttpResult getTreeInfo(@RequestParam("tree_id") String treeId){
        return HttpResult.success(treeInfoService.getTreeInfoByTreeId(treeId));
    }

    @ApiOperation("模糊查询树木信息，通过树木id、树木中文名、拉丁名、俗名")
    @GetMapping("/info/fuzzy")
    public HttpResult getTreeInfoFuzzy(@RequestParam("data") String data){
        return HttpResult.success(treeInfoService.getInfosFuzzyQuery(data));
    }

    @ApiOperation("上传/修改树木信息，记录操作人")
    @PutMapping("/info")
    public HttpResult saveTreeInfo(@RequestBody TreeInfo treeInfo, @RequestParam("username") String username){
        return HttpResult.success(treeInfoService.saveTreeInfo(treeInfo, username));
    }

    @ApiOperation("获取树木信息列表，分页查询")
    @GetMapping("/list")
    public HttpResult getTreeList(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "desc", required = false, defaultValue = "0") int desc) {
        return HttpResult.success(treeInfoService.getTreeList(page, size, desc));
    }

    @ApiOperation("获取树木信息列表，分页+排序")
    @GetMapping("/info_sorted")
    public HttpResult getTreeListSorted(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("keys") String[] keys,
            @RequestParam(name = "asc") int asc) {
        return HttpResult.success(treeInfoService.getTreeListSorted(page, size, keys, asc));
    }

    @ApiOperation("获取某个城市内拥有的树木信息列表")
    @GetMapping("/info/{city}")
    public HttpResult getTreeListByCity(@PathVariable("city") String city) {
        return HttpResult.success(treeInfoService.getTreeList(city));
    }

    @ApiOperation("获取所有的树木信息列表")
    @GetMapping("/info/all")
    public HttpResult getTreeList() {
        return HttpResult.success(treeInfoService.getTreeListAll());
    }

    @ApiOperation("有条件的查询树木数据")
    @GetMapping("/condition")
    public HttpResult getUserBySearch(@RequestParam("condition") String condition,
                                      @RequestParam("value") String value,
                                      @RequestParam("page") int page,
                                      @RequestParam("size") int size) throws Exception {
        return HttpResult.success(treeInfoService.getTreeListBySearch(condition, value, page, size));
    }

    @ApiOperation("为某棵树木（tree_id）设置对应的硬件节点标识（imsi）")
    @PostMapping("/imsi")
    public HttpResult setImsiForTree(@RequestParam("tree_id") String treeId,@RequestParam("imsi") String imsi) {
        return HttpResult.success(treeInfoService.setImsiForTree(treeId, imsi));
    }

    @ApiOperation("查询某棵树木的imsi")
    @GetMapping("/imsi")
    public HttpResult findImsiForTree(@RequestParam("tree_id") String treeId){
        return HttpResult.success(treeInfoService.findImsiForTree(treeId));
    }
}
