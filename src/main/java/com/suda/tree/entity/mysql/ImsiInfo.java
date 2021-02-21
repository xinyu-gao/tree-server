package com.suda.tree.entity.mysql;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "imsi_info")
public class ImsiInfo {

    /**
     * IMSI
     */
    @Id
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
    private float carbonDioxide;

    /**
     * 倾斜度
     */
    @Column(name = "slant")
    private float slant;

    /**
     * 是否在线
     */
    @Column(name = "is_online")
    private boolean isOnline;

    /**
     * 最后一次数据发送时间
     */
    @Column(name = "send_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date sendTime;
}
