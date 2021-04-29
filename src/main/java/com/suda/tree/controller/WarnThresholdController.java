package com.suda.tree.controller;

import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.entity.mysql.WarnThreshold;
import com.suda.tree.service.WarnThresholdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("警告阈值")
@RestController
@RequestMapping("/warn/threshold")
public class WarnThresholdController {

    @Autowired
    private WarnThresholdService warnThresholdService;

    @ApiOperation("获取警告阈值")
    @GetMapping("")
    public HttpResult getWarnThreshold(@RequestParam("tree_id") String treeId) {
        return HttpResult.success(warnThresholdService.getWarnThreshold(treeId));
    }

    @ApiOperation("修改警告阈值，需要传输所有值")
    @PostMapping("")
    public HttpResult setWarnThreshold(@RequestBody WarnThreshold warnThreshold) {
        var w = warnThresholdService.setWarnThreshold(warnThreshold);
        return w == null ? HttpResult.failed("修改失败") : HttpResult.success(w);
    }

}
