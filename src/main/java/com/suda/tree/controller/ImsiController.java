package com.suda.tree.controller;

import com.alibaba.fastjson.JSON;
import com.suda.tree.dao.ImsiInfoHistoryRepository;
import com.suda.tree.dao.ImsiInfoRepository;
import com.suda.tree.dto.param.NodeParam;
import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.dto.result.LineDataResult;
import com.suda.tree.entity.mysql.ImsiDetectInfo;
import com.suda.tree.entity.mysql.ImsiDetectInfoHistory;
import com.suda.tree.entity.mysql.ImsiInfo;
import com.suda.tree.entity.mysql.ImsiInfoHistory;
import com.suda.tree.service.ImsiDetectInfoService;
import com.suda.tree.service.ImsiInfoService;
import com.suda.tree.service.WebSocketService;
import com.suda.tree.util.TransferUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.EncodeException;
import java.io.IOException;
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
    private ImsiInfoService imsiInfoService;

    @Autowired
    private ImsiDetectInfoService imsiDetectInfoService;

    @Autowired
    private WebSocketService webSocketService;

    @GetMapping("/newest")
    public HttpResult getNodeInfoByImsi(@RequestParam("imsi") String imsi) {
        Optional info = imsiInfoRepository.findByImsi(imsi);
        return HttpResult.success(info.isEmpty() ? new HashMap<>() : info.get());
    }

    @GetMapping("/history")
    public HttpResult getNodeHistoryInfoByImsi(
            @RequestParam("imsi") String imsi,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return HttpResult.success(imsiInfoService.getNodeHistoryInfoByImsi(imsi, page, size));
    }


    @ApiOperation("接收树莓派节点发送来的监测数据")
    @PostMapping()
    public HttpResult saveNodeInfo(@RequestBody NodeParam node) throws IOException, EncodeException {
        ImsiInfo imsiInfo = TransferUtil.NodeParamToImsiInfo(node);
        ImsiInfoHistory imsiInfoHistory = TransferUtil.NodeParamToImsiInfoHistory(node);
        imsiInfoRepository.save(imsiInfo);
        imsiInfoHistoryRepository.save(imsiInfoHistory);
        webSocketService.sendInfoForAll("数据更新");
        return HttpResult.success(node.toString());
    }

    @ApiOperation("接收树莓派节点发送来的缺陷成像数据")
    @PostMapping("/defect")
    public HttpResult saveNodeDetectInfo(@RequestBody ImsiDetectInfo node) throws IOException, EncodeException {
        log.info(JSON.toJSONString(node));
        imsiDetectInfoService.saveImsiDetectInfo(node);
        imsiDetectInfoService.saveImsiDetectHInfoHistory(
                new ImsiDetectInfoHistory(node.getImsi(), node.getData()));
        webSocketService.sendInfoForAll("数据更新");
        return HttpResult.success(node);
    }

    @ApiOperation("获取某个监测节点的曲线数据")
    @GetMapping("/line_data")
    public HttpResult getLineData(@RequestParam("imsi") String imsi) {
        LineDataResult result = imsiInfoService.getLineDataByIMSI(imsi);
        return HttpResult.success(result);
    }

    @ApiOperation("获取监测节点的缺陷数据")
    @GetMapping("/defect")
    public HttpResult getNodeDetectInfo(@RequestParam("imsi") String imsi) {
        return HttpResult.success(imsiDetectInfoService.getImsiDetectInfoByImsi(imsi));
    }
}
