package com.suda.tree.controller;

import com.suda.tree.dao.ImsiInfoRepository;
import com.suda.tree.dto.result.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/imsi")
public class ImsiController {

    @Autowired
    private ImsiInfoRepository imsiInfoRepository;

    @GetMapping("/newest")
    public HttpResult getNodeInfoByImsi(@RequestParam("imsi") String imsi){
        Optional info = imsiInfoRepository.findByImsi(imsi);
        return HttpResult.success(info.isEmpty() ? new HashMap<>() : info.get());
    }

}
