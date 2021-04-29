package com.suda.tree.entity.mysql;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 树莓派监测数据
 */
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
     * 倾斜度
     */
    @Column(name = "slant")
    private float slant;

    /**
     * 是否在线
     * 定时搜索一遍数据库，如果有某个发送时间超过一个定值，就判定为离线
     */
    @Column(name = "is_online")
    private boolean isOnline;

    /**
     * 最后一次数据发送时间
     */
    @Column(name = "send_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date sendTime;

    public ImsiInfo(String imsi, float temp, float humidity, float slant, boolean isOnline, Date sendTime) {
        this.imsi = imsi;
        this.temp = temp;
        this.humidity = humidity;
        this.slant = slant;
        this.isOnline = isOnline;
        this.sendTime = sendTime;
    }
    public ImsiInfo(){}
}
