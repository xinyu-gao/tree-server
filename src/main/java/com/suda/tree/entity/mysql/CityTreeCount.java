package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 城市拥有的树木的数量
 */
@Entity
@Data
@Table(name = "city_tree_count")
public class CityTreeCount {


    @Id
    @Column(name = "city")
    private String city;


    /**
     * 树木数量
     */
    @Column(name = "count")
    private int count;

    public CityTreeCount(String city, int count) {
        this.city = city;
        this.count = count;
    }

    public CityTreeCount(){}
}
