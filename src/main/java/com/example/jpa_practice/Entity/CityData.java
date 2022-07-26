package com.example.jpa_practice.Entity;

public class CityData {
    private String city_name;
    private String district_name;
    private Integer city_population;
    public CityData() {
    }
    public CityData(String city_name, String district_name, Integer city_population) {
        this.city_name = city_name;
        this.district_name = district_name;
        this.city_population = city_population;
    }
    public String getCity_name() {
        return city_name;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
    public String getDistrict_name() {
        return district_name;
    }
    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }
    public Integer getCity_population() {
        return city_population;
    }
    public void setCity_population(Integer city_population) {
        this.city_population = city_population;
    }

    
}
