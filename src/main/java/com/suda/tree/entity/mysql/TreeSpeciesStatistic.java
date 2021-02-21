package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tree_species_statistic")
public class TreeSpeciesStatistic implements Serializable {

    /**
     * 省（自治区、直辖市）
     */
    @Id
    private String province;

    /**
     * 合计
     */
    private int total;

    /**
     * 科
     */
    private int family;

    /**
     * 属
     */
    private int genus;

    /**
     * 种
     */
    private int species;

    /**
     * 树种1
     */
    @Column(name = "speciel1_name")
    private String specie1Name;

    /**
     * 树种2
     */
    @Column(name = "speciel2_name")
    private String specie2Name;

    /**
     * 树种3
     */
    @Column(name = "speciel3_name")
    private String specie3Name;

    /**
     * 树种4
     */
    @Column(name = "speciel4_name")
    private String specie4Name;

    /**
     * 树种5
     */
    @Column(name = "speciel5_name")
    private String specie5Name;

    /**
     * 树种1数量
     */
    private int speciel1;

    /**
     * 树种2数量
     */
    private int speciel2;

    /**
     * 树种3数量
     */
    private int speciel3;

    /**
     * 树种4数量
     */
    private int speciel4;

    /**
     * 树种5数量
     */
    private int speciel5;

    /**
     * 其他树种数量
     */
    @Column(name = "speciel_others")
    private int specielOthers;


}
