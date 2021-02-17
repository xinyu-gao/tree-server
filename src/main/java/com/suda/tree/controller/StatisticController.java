package com.suda.tree.controller;

import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.service.ExistedProvinceAndCityService;
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

    @Autowired
    private ExistedProvinceAndCityService existedProvinceAndCityService;

    @GetMapping("/grade")
    public HttpResult getTreeGrade(@RequestParam("province") String province){
        return HttpResult.success(statisticService.getTreeGrade(province));
    }

    @GetMapping("/province_city")
    public HttpResult getExistedProvinceAndCity(){
        return HttpResult.success(existedProvinceAndCityService.getExistedProvinceAndCityStatistic());
    }
}
