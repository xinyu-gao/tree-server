package com.suda.tree.dto;

import lombok.Data;

import java.util.List;

@Data
public class City {

    private List<Location> municipalities;
    private List<CityLocation> provinces;
    private List<Location> other;


    @Data
    public static
    class Location{
        private String n;

        private String g;
    }

    @Data
    public static
    class CityLocation{
        private String n;

        private String g;

        private List<Location> cities;

    }
}
