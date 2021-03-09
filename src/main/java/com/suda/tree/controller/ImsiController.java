package com.suda.tree.controller;

import cn.hutool.core.date.DateUtil;
import com.suda.tree.dao.ImsiInfoHistoryRepository;
import com.suda.tree.dao.ImsiInfoRepository;
import com.suda.tree.dto.param.NodeParam;
import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.dto.result.LineDataResult;
import com.suda.tree.entity.mysql.ImsiInfo;
import com.suda.tree.entity.mysql.ImsiInfoHistory;
import com.suda.tree.service.ImsiInfoHistoryService;
import com.suda.tree.util.TransferUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/imsi")
public class ImsiController {

    @Autowired
    private ImsiInfoRepository imsiInfoRepository;

    @Autowired
    private ImsiInfoHistoryRepository imsiInfoHistoryRepository;

    @Autowired
    private ImsiInfoHistoryService imsiInfoHistoryService;

    @GetMapping("/newest")
    public HttpResult getNodeInfoByImsi(@RequestParam("imsi") String imsi) {
        Optional info = imsiInfoRepository.findByImsi(imsi);
        return HttpResult.success(info.isEmpty() ? new HashMap<>() : info.get());
    }


    @ApiOperation("接收树莓派节点发送来的数据")
    @PostMapping()
    public HttpResult saveNodeInfo(@RequestBody Object node) {
//        ImsiInfo imsiInfo = TransferUtil.NodeParamToImsiInfo(node);
//        ImsiInfoHistory imsiInfoHistory = TransferUtil.NodeParamToImsiInfoHistory(node);
//        imsiInfoRepository.save(imsiInfo);
//        imsiInfoHistoryRepository.save(imsiInfoHistory);
        return HttpResult.success(node.toString());
    }

    @ApiOperation("获取某个检测节点的曲线数据")
    @GetMapping("/line_data")
    public HttpResult getLineData(@RequestParam("imsi") String imsi) {
        LineDataResult result = imsiInfoHistoryService.getLineDataByIMSI(imsi);
        return HttpResult.success(result);
    }
}
