package com.suda.tree.entity.mysql;

import com.suda.tree.util.JpaConverterListJson;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 树莓派缺陷历史数据
 */
@Entity
@Data
@Table(name = "imsi_detect_info_history")
public class ImsiDetectInfoHistory {

    /**
     * IMSI
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "imsi")
    private String imsi;

    /**
     * 缺陷数据
     */
    @Column(name = "data", columnDefinition = "MEDIUMTEXT")
    @Convert(converter = JpaConverterListJson.class)
    private List<List<Float>> data;

    /**
     * 最后一次数据发送时间
     */
    @Column(name = "send_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date sendTime;

    public ImsiDetectInfoHistory(String imsi, List<List<Float>> data) {
        this.imsi = imsi;
        this.data = data;
    }

    public ImsiDetectInfoHistory() {

    }
}
