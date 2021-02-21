package com.suda.tree.entity.mysql;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "city_coordinate")
public class CityCoordinate {

    @Id
    @Column(name = "city")
    private String city;

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

    public CityCoordinate(String city, double longitude, double latitude) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public CityCoordinate() {
    }
}
