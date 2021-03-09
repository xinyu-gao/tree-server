package com.suda.tree.dto.param;

import lombok.Data;

import javax.persistence.Column;


@Data
public class NodeParam {

    /**
     * IMSI
     */
    private String imsi;

    /**
     * 温度
     */
    private float temp;

    /**
     * 湿度
     */
    private float humidity;

    /**
     * 倾斜度
     */
    private float slant;

    /**
     * 二氧化碳浓度
     */
    private float carbonDioxide;


}
