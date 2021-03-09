package com.suda.tree.service.impl;

import com.suda.tree.dao.ImsiInfoHistoryRepository;
import com.suda.tree.dto.result.LineDataResult;
import com.suda.tree.entity.mysql.ImsiInfoHistory;
import com.suda.tree.service.ImsiInfoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImsiInfoHistoryServiceImpl implements ImsiInfoHistoryService {

    @Autowired
    private ImsiInfoHistoryRepository imsiInfoHistoryRepository;

    @Override
    public LineDataResult getLineDataByIMSI(String imsi) {
        Page<ImsiInfoHistory> nodeList = imsiInfoHistoryRepository.findByImsiOrderBySendTimeDesc(imsi, PageRequest.of(0, 20));
        List<String> lineTimeList = new LinkedList<>();
        List<Float> lineTempList = new LinkedList<>();
        List<Float> lineHumidityList = new LinkedList<>();
        List<Float> lineSlantList = new LinkedList<>();
        List<Float> lineCarbonDioxideListList = new LinkedList<>();
        for (ImsiInfoHistory node : nodeList.getContent()) {
            lineTimeList.add(0, node.getSendTime().toString().substring(11, 16));
            lineTempList.add(0, node.getTemp());
            lineHumidityList.add(0, node.getHumidity());
            lineSlantList.add(0, node.getSlant());
            lineCarbonDioxideListList.add(0, node.getCO2());
        }
        ;
        return new LineDataResult(imsi, lineTimeList, lineTempList, lineHumidityList, lineSlantList, lineCarbonDioxideListList);
    }
}