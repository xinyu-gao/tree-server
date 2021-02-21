package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.*;


import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tree_info")
public class TreeInfo implements Serializable {

    /**
     * 古树编号
     */
    @Id
    @Column(name = "tree_id")
    private String treeId;

    /**
     * 调查顺序号
     */
    @Column(name = "survey_number")
    private String surveyNumber;

    /**
     * 中文名
     */
    @Column(name = "chinese_name")
    private String chineseName;

    /**
     * 俗名
     */
    @Column(name = "alias")
    private String alias;

    /**
     * 拉丁名
     */
    @Column(name = "latin_name")
    private String latinName;

    /**
     * 科
     */
    private String family;

    /**
     * 属
     */
    private String genus;

    /**
     * 种
     */
    @Column(name = "species")
    private String species;

    /**
     * 位置-省
     */
    @Column(name = "location_province")
    private String locationProvince;

    /**
     * 位置-市
     */
    @Column(name = "location_city")
    private String locationCity;

    /**
     * 位置-区县
     */
    @Column(name = "location_district")
    private String locationDistrict;

    /**
     * 位置-详细地址
     */
    @Column(name = "location_detail")
    private String locationDetail;

    /**
     * WGS - 经度
     */
    @Column(name = "longitude")
    private double longitude;

    /**
     * WGS - 纬度
     */
    @Column(name = "latitude")
    private double latitude;


    /**
     * 生长场所：乡村、城区
     */
    @Column(name = "growth_place")
    private String growthPlace;


    /**
     * 分布特点：散生、群状
     */
    @Column(name = "dist_ch")
    private String distCH;

    /**
     * 权属：国有、集体、个人、其他
     */
    @Column(name = "owner_ship")
    private String ownerShip;

    /**
     * 树龄
     */
    @Column(name = "age")
    private int age;

    /**
     * 古树等级
     */
    @Column(name = "grade")
    private String grade;

    /**
     * 树高
     */
    @Column(name = "height")
    private int height;

    /**
     * 胸围
     */
    @Column(name = "breast_diameter")
    private int breastDiameter;

    /**
     * 冠幅-平均
     */
    @Column(name = "crown_width_average")
    private int crownWidthAverage;

    /**
     * 冠幅-东西
     */
    @Column(name = "crown_width_average_ew")
    private int crownWidthEW;

    /**
     * 冠幅-南北
     */
    @Column(name = "crown_width_ns")
    private int crownWidthNS;


    /**
     * 海拔
     */
    @Column(name = "altitud")
    private int altitude;

    /**
     * 坡向
     */
    @Column(name = "aspect")
    private String aspect;


    /**
     * 坡度
     */
    @Column(name = "slope")
    private String slope;

    /**
     * 坡位
     */
    @Column(name = "slope_position")
    private String slopePosition;

    /**
     * 土壤类型
     */
    @Column(name = "soil_type")
    private String soilType;

    /**
     * 生长势：正常、衰弱、濒危、死亡
     */
    @Column(name = "growth_vigor")
    private String growthVigor;

    /**
     * 生长环境：好、中、差
     */
    @Column(name = "env")
    private String env;

    /**
     * 影响生长环境因素
     */
    @Column(name = "env_factor")
    private String envFactor;

    /**
     * 新增树木原因：树龄增长、遗漏树木、异地移植
     */
    /**
     * 管护单位（个人）
     */
    @Column(name = "management")
    private String management;

    /**
     * 管护人
     */
    @Column(name = "guardian")
    private String guardian;

    /**
     * 奇特性描述
     */
    @Column(name = "peculiar_detail")
    private String peculiarDetail;

    /**
     * 保护现状：避雷针、护栏、支撑、封堵树洞、砌树池、包树箍、树池透气铺装、其他
     */
    @Column(name = "protection_status")
    private String protectionStatus;

    /**
     * 保护现状：复壮沟、渗井、透气管、幼树靠接、土壤改良、叶面施肥、其他
     */
    @Column(name = "conserve_status")
    private String conserveStatus;

    /**
     * 详细描述
     */
    @Column(name = "detail_info")
    private String detailInfo;

    /**
     * 调查人ID
     */
    @Column(name = "declarer_id")
    private Long declarerId;

    /**
     * 审核人ID
     */
    @Column(name = "reviewer_id")
    private Long reviewerId;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(name = "imsi")
    private String imsi;
}
