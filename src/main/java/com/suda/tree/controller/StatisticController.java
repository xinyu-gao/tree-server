package com.suda.tree.controller;

import com.suda.tree.dto.HttpResult;
import com.suda.tree.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/grade")
    public HttpResult getTreeGrade(@RequestParam("province") String province){
        return HttpResult.success(statisticService.getTreeGrade(province));
    }
}
