package com.suda.tree.dto.result;

import lombok.Data;

import java.util.List;

@Data
public class LineDataResult {

    private String imsi;

    private List<String> lineTimeList;

    private List<Float> lineTempList;

    private List<Float> lineHumidityList;

    private List<Float> lineSlantList;

    public LineDataResult(String imsi,
                          List<String> lineTimeList,
                          List<Float> lineTempList,
                          List<Float> lineHumidityList,
                          List<Float> lineSlantList) {
        this.imsi = imsi;
        this.lineTimeList = lineTimeList;
        this.lineTempList = lineTempList;
        this.lineHumidityList = lineHumidityList;
        this.lineSlantList = lineSlantList;
    }
}
