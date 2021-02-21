package com.suda.tree.entity.mysql;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Entity
@Data
@Table(name = "imsi_info_history")
public class ImsiInfoHistory {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

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
     * 是否在线
     */
    @Column(name = "is_online")
    private boolean isOnline;

    /**
     * 最后一次数据发送时间
     */
    @Column(name = "send_time")
    private boolean sendTime;
}
