package com.suda.tree.entity.mysql;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 树莓派监测历史数据
 */
@Entity
@Data
@Table(name = "imsi_info_history")
public class ImsiInfoHistory {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * IMSI
     */
    @Column(name = "imsi")
    private String imsi;

    /**
     * 温度
     */
    @Column(name = "temp")
    private float temp;

    /**
     * 湿度
     */
    @Column(name = "humidity")
    private float humidity;

    /**
     * 二氧化碳浓度
     */
    @Column(name = "co2")
    private float CO2;

    /**
     * 倾斜度
     */
    @Column(name = "slant")
    private float slant;


    /**
     * 最后一次数据发送时间
     */
    @Column(name = "send_time")
    private Date sendTime;
}
