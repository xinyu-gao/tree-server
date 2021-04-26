package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 警报阈值
 */
@Entity
@Data
@Table(name = "warn_threshold")
public class WarnThreshold {

    @Id
    private Long id;

    /**
     * 温度上限
     */
    @Column(name = "temp_upper")
    private Float tempUpper;

    /**
     * 温度下限
     */
    @Column(name = "temp_lower")
    private Float tempLower;

    /**
     * 湿度上限
     */
    @Column(name = "humidity_upper")
    private Float humidityUpper;

    /**
     * 湿度下限
     */
    @Column(name = "humidity_lower")
    private Float humidityLower;

    /**
     * 倾斜度上限
     */
    @Column(name = "slant_upper")
    private Float slantUpper;

    /**
     * 倾斜度下限
     */
    @Column(name = "slant_lower")
    private Float slantLower;
}
