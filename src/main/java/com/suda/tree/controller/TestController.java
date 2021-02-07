package com.suda.tree.controller;

import com.suda.tree.dto.HttpResult;
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

//    @PostMapping("/pic")
//    public HttpResult pic(String id, String url) throws Exception {
//        treePicService.upsert(id, url);
//        return HttpResult.success();
//    }
}
