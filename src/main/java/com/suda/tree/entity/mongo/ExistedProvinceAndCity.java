package com.suda.tree.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "existed_province_and_city")
public class ExistedProvinceAndCity {
    public ExistedProvinceAndCity(String province, List<String> cityList) {
        this.province = province;
        this.cityList = cityList;
    }

    @Id
    @Indexed
    private String province;

    @Field(name = "city_list")
    private List<String> cityList;
}
