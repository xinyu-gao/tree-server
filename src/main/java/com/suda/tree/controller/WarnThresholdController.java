package com.suda.tree.controller;

import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.entity.mysql.WarnThreshold;
import com.suda.tree.service.WarnThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warn/threshold")
public class WarnThresholdController {

    @Autowired
    private WarnThresholdService warnThresholdService;


    @GetMapping("")
    public HttpResult getWarnThreshold() {
        return HttpResult.success(warnThresholdService.getWarnThreshold());
    }

    @PostMapping("")
    public HttpResult setWarnThreshold(@RequestBody WarnThreshold warnThreshold) {
        var w = warnThresholdService.setWarnThreshold(warnThreshold);
        return w == null ? HttpResult.failed("修改失败") : HttpResult.success(w);
    }


}
