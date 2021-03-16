package com.suda.tree.entity.mysql;

import com.suda.tree.util.JpaConverterListJson;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "existed_province_and_city")
public class ExistedProvinceAndCity {
    public ExistedProvinceAndCity(String province, List<String> cityList) {
        this.province = province;
        this.cityList = cityList;
    }

    public ExistedProvinceAndCity() {

    }

    @Id
    @Column(name = "province")
    private String province;

    @Column(name = "city_list", columnDefinition = "TEXT")
    @Convert(converter = JpaConverterListJson.class)
    private List<String> cityList;


}
