package com.suda.tree.controller;

import com.suda.tree.dao.TreeInfoRepository;
import com.suda.tree.dto.HttpResult;
import com.suda.tree.service.ExistedProvinceAndCityService;
import com.suda.tree.service.TreeInfoService;
import com.suda.tree.service.TreePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private TreePicService treePicService;

    @Autowired
    private TreeInfoRepository treeInfoRepository;

    @Autowired
    private TreeInfoService treeInfoService;

    @Autowired
    private ExistedProvinceAndCityService existedProvinceAndCityService;

    @PostMapping("/1")
    public HttpResult pic(){
        return HttpResult.success(existedProvinceAndCityService.saveExistedProvinceAndCityStatistic());
    }
}
